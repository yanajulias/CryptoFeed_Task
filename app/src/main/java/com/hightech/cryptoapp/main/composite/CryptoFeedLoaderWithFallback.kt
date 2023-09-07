package com.hightech.cryptoapp.main.composite

import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.CryptoFeedResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoFeedLoaderWithFallback(
    private val primary: CryptoFeedLoader,
    private val fallback: CryptoFeedLoader
) : CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        primary.load().collect { cryptoFeedResult ->
            when (cryptoFeedResult) {
                is CryptoFeedResult.Success -> emit(cryptoFeedResult)
                is CryptoFeedResult.Failure -> {
                    fallback.load().collect { emit(it) }
                }
            }

        }
    }
}