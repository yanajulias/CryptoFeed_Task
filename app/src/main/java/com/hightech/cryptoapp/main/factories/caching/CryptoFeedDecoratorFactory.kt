package com.hightech.cryptoapp.main.factories.caching

import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.domain.usecases.InsertCryptoFeed
import com.hightech.cryptoapp.main.decorators.CryptoFeedCacheDecorator

class CryptoFeedDecoratorFactory {
    companion object {
        fun createCryptoFeedCacheDecorator(
            cryptoFeedLoader: CryptoFeedLoader,
            cache: InsertCryptoFeed
        ): CryptoFeedLoader {
            return CryptoFeedCacheDecorator(cryptoFeedLoader, cache)
        }
    }
}