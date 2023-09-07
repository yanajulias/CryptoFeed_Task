package com.hightech.cryptoapp.main.factories.caching

import android.content.Context
import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.InsertCryptoFeed
import com.hightech.cryptoapp.frameworks.database.usecases.InsertCryptoFeedUseCase
import com.hightech.cryptoapp.frameworks.database.usecases.LoadCryptoFeedLocalUseCase

class CryptoFeedLocalUseCaseFactory {
    companion object {
        fun createLoadCryptoFeedLocalUseCase(context: Context): CryptoFeedLoader {
            val cryptoFeedDao = CryptoFeedDaoFactory.createCryptoFeedDao(context)
            return LoadCryptoFeedLocalUseCase(
                cryptoFeedDao
            )
        }

        fun createInsertCryptoFeedLocalUseCase(context: Context): InsertCryptoFeed {
            val cryptoFeedDao = CryptoFeedDaoFactory.createCryptoFeedDao(context)
            return InsertCryptoFeedUseCase(
                cryptoFeedDao
            )
        }
    }
}