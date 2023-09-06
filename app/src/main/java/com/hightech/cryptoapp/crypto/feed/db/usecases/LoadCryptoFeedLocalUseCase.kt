package com.hightech.cryptoapp.crypto.feed.db.usecases

import com.hightech.cryptoapp.crypto.feed.db.CryptoFeedDao
import com.hightech.cryptoapp.crypto.feed.db.LocalCryptoFeedItem.Companion.toDomain
import com.hightech.cryptoapp.crypto.feed.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.crypto.feed.domain.usecases.CryptoFeedResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadCryptoFeedLocalUseCase(
    private val cryptoFeedDao: CryptoFeedDao
) : CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedDao.getAll().collect { localCryptoFeedItems ->
            val cryptoFeedItems = localCryptoFeedItems.map {
                it.toDomain()
            }
            emit(CryptoFeedResult.Success(cryptoFeedItems))
        }
    }
}