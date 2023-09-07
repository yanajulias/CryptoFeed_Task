package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.frameworks.http.usecases.LoadCryptoFeedRemoteUseCase

class CryptoFeedRemoteLoaderFactory {
    companion object {
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return LoadCryptoFeedRemoteUseCase(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}