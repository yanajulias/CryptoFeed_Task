package com.hightech.cryptoapp.domain.usecases

import com.hightech.cryptoapp.domain.model.CryptoFeedItem

interface InsertCryptoFeed {
    suspend fun insert(cryptoFeedItems: List<CryptoFeedItem>)
}