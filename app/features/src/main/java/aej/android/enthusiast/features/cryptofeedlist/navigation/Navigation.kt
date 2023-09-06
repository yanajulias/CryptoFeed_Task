package aej.android.enthusiast.features.cryptofeedlist.navigation

import aej.android.enthusiast.features.CryptoFeedRoute
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hightech.cryptoapp.main.factories.ViewModelFactory

const val cryptoGraphRoute = "crypto_graph_route"
const val cryptoFeedRoute = "crypto_feed_route"

fun NavGraphBuilder.cryptoGraph(
    onCryptoClick: (aej.android.enthusiast.domain.model.CryptoFeedItem) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = cryptoGraphRoute,
        startDestination = cryptoFeedRoute
    ) {
        composable(
            route = cryptoFeedRoute
        ) {
            val context = LocalContext.current
            CryptoFeedRoute(
                viewModel = viewModel(factory = ViewModelFactory.provideViewModel(context)),
                onNavigateToCryptoDetails = onCryptoClick
            )
        }
        nestedGraphs()
    }
}