package com.hightech.cryptoapp.main.decorators

import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.CryptoFeedResult
import com.hightech.cryptoapp.domain.usecases.InsertCryptoFeed
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