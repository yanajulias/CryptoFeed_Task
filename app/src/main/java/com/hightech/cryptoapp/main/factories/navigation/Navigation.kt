package com.hightech.cryptoapp.main.factories.navigation

import com.hightech.cryptoapp.domain.model.CryptoFeedItem
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hightech.cryptoapp.features.CryptoFeedRoute
import com.hightech.cryptoapp.main.factories.ViewModelFactory

const val cryptoGraphRoute = "crypto_graph_route"
const val cryptoFeedRoute = "crypto_feed_route"

fun NavGraphBuilder.cryptoGraph(
    onCryptoClick: (CryptoFeedItem) -> Unit,
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