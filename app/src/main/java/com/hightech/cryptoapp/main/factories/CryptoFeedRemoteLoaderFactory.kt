package com.hightech.cryptoapp.main.factories

import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.frameworks.http.usecases.LoadCryptoFeedRemoteUseCase

class CryptoFeedRemoteLoaderFactory {
    companion object {
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return LoadCryptoFeedRemoteUseCase(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}