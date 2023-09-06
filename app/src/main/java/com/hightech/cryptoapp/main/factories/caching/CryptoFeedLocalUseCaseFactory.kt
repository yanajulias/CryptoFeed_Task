package com.hightech.cryptoapp.main.factories.caching

import android.content.Context
import aej.android.enthusiast.frameworks.database.usecases.InsertCryptoFeedUseCase
import aej.android.enthusiast.frameworks.database.usecases.LoadCryptoFeedLocalUseCase
import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.domain.usecases.InsertCryptoFeed

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