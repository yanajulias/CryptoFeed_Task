package com.hightech.cryptoapp.main.factories.caching

import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.InsertCryptoFeed
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