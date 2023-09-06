package com.hightech.cryptoapp.crypto.feed.domain.usecases

import com.hightech.cryptoapp.crypto.feed.domain.model.CryptoFeedItem

interface InsertCryptoFeed {
    suspend fun insert(cryptoFeedItems: List<CryptoFeedItem>)
}