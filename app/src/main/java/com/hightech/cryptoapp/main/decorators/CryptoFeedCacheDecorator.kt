package com.hightech.cryptoapp.main.decorators

import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.domain.usecases.CryptoFeedResult
import aej.android.enthusiast.domain.usecases.InsertCryptoFeed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoFeedCacheDecorator (
    private val cryptoFeedLoader: CryptoFeedLoader,
    private val cache : InsertCryptoFeed
): CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedLoader.load().collect{ result ->
            if (result is CryptoFeedResult.Success){
                cache.insert(result.cryptoFeedItems)
            }
            emit(result)
        }
    }
}