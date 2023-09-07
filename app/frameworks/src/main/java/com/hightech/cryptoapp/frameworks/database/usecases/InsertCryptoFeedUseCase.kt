package com.hightech.cryptoapp.frameworks.database.usecases

import com.hightech.cryptoapp.domain.model.CryptoFeedItem
import com.hightech.cryptoapp.domain.usecases.InsertCryptoFeed
import com.hightech.cryptoapp.frameworks.database.CryptoFeedDao

import com.hightech.cryptoapp.frameworks.database.LocalCryptoFeedItem.Companion.fromDomain

class InsertCryptoFeedUseCase(
    private val cryptoFeedDao: CryptoFeedDao
) : InsertCryptoFeed {
    override suspend fun insert(cryptoFeedItems: List<CryptoFeedItem>) {
        val localCryptoFeedItem = cryptoFeedItems.fromDomain()
        cryptoFeedDao.insertAll(localCryptoFeedItem)
    }
}