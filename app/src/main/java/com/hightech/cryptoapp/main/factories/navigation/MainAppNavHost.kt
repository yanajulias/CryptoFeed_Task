package com.hightech.cryptoapp.main.factories.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import aej.android.enthusiast.features.cryptofeeddetail.navigation.cryptoDetailScreen
import aej.android.enthusiast.features.cryptofeeddetail.navigation.navigateToCryptoDetails
import aej.android.enthusiast.features.cryptofeedlist.navigation.cryptoGraph

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = aej.android.enthusiast.features.cryptofeedlist.navigation.cryptoGraphRoute,
) {
    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        cryptoGraph(
            onCryptoClick = navHostController::navigateToCryptoDetails
        ) {
            cryptoDetailScreen(
                popBackStack = navHostController::popBackStack
            )
        }
    }
}