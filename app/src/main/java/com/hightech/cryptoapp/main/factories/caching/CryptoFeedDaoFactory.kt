package com.hightech.cryptoapp.main.factories.caching

import android.content.Context
import com.hightech.cryptoapp.frameworks.DbFactory
import com.hightech.cryptoapp.frameworks.database.CryptoFeedDao

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