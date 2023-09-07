package com.hightech.cryptoapp.frameworks.database.usecases

import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.CryptoFeedResult
import com.hightech.cryptoapp.frameworks.database.CryptoFeedDao
import com.hightech.cryptoapp.frameworks.database.LocalCryptoFeedItem.Companion.toDomain
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