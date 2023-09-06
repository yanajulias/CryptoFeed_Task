package com.hightech.cryptoapp.main.factories.caching

import com.hightech.cryptoapp.crypto.feed.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.main.composite.CryptoFeedLoaderWithFallback

class CryptoFeedCompositeFactory {
    companion object {
        fun createCryptoFeedLoaderWithFallback(
            primary: CryptoFeedLoader,
            fallback: CryptoFeedLoader
        ): CryptoFeedLoaderWithFallback {
            return CryptoFeedLoaderWithFallback(
                primary = primary,
                fallback = fallback
            )
        }
    }
}