package com.hightech.cryptoapp.main.factories

import aej.android.enthusiast.frameworks.http.CryptoFeedHttpClient
import aej.android.enthusiast.frameworks.http.CryptoFeedRetrofitHttpClient

class CryptoFeedHttpClientFactory {
    companion object {
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}