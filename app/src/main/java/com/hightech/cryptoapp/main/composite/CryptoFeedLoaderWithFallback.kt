package com.hightech.cryptoapp.main.composite

import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.domain.usecases.CryptoFeedResult
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