package com.hightech.cryptoapp.crypto.feed.domain.usecases

import com.hightech.cryptoapp.crypto.feed.domain.model.CryptoFeedItem
import kotlinx.coroutines.flow.Flow

sealed class CryptoFeedResult {
    data class Success(val cryptoFeedItems: List<CryptoFeedItem>) : CryptoFeedResult()
    data class Failure(val throwable: Throwable) : CryptoFeedResult()
}

interface CryptoFeedLoader {
    fun load(): Flow<CryptoFeedResult>
}