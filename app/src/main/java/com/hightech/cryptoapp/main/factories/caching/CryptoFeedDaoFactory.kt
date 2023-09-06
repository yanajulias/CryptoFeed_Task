package com.hightech.cryptoapp.main.factories.caching

import android.content.Context
import aej.android.enthusiast.frameworks.database.CryptoFeedDao
import aej.android.enthusiast.frameworks.DbFactory

class CryptoFeedDaoFactory {
    companion object {
        fun createCryptoFeedDao(context: Context): CryptoFeedDao {
            return createAppDatabase(context).cryptoFeedDao()
        }

        private fun createAppDatabase(context: Context): DbFactory.AppDatabase{
            return DbFactory.createDatabase(context)
        }
    }
}