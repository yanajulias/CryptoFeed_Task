package com.hightech.cryptoapp.main.factories

import com.hightech.cryptoapp.frameworks.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.frameworks.http.CryptoFeedRetrofitHttpClient


class CryptoFeedHttpClientFactory {
    companion object {
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}