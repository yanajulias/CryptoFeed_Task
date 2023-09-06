package com.hightech.cryptoapp.crypto.feed.db.usecases

import com.hightech.cryptoapp.crypto.feed.db.CryptoFeedDao
import com.hightech.cryptoapp.crypto.feed.db.LocalCryptoFeedItem.Companion.fromDomain
import com.hightech.cryptoapp.crypto.feed.domain.model.CryptoFeedItem
import com.hightech.cryptoapp.crypto.feed.domain.usecases.InsertCryptoFeed

class InsertCryptoFeedUseCase(
    private val cryptoFeedDao: CryptoFeedDao
) : InsertCryptoFeed {
    override suspend fun insert(cryptoFeedItems: List<CryptoFeedItem>) {
        val localCryptoFeedItem = cryptoFeedItems.fromDomain()
        cryptoFeedDao.insertAll(localCryptoFeedItem)
    }
}