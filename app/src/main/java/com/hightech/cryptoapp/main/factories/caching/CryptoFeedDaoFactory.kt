package com.hightech.cryptoapp.main.factories.caching

import android.content.Context
import com.hightech.cryptoapp.crypto.feed.db.CryptoFeedDao
import com.hightech.cryptoapp.frameworks.DbFactory

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