package aej.android.enthusiast.frameworks.database.usecases

import aej.android.enthusiast.domain.model.CryptoFeedItem
import aej.android.enthusiast.domain.usecases.InsertCryptoFeed
import aej.android.enthusiast.frameworks.database.CryptoFeedDao
import aej.android.enthusiast.frameworks.database.LocalCryptoFeedItem.Companion.fromDomain

class InsertCryptoFeedUseCase(
    private val cryptoFeedDao: CryptoFeedDao
) : InsertCryptoFeed {
    override suspend fun insert(cryptoFeedItems: List<CryptoFeedItem>) {
        val localCryptoFeedItem = cryptoFeedItems.fromDomain()
        cryptoFeedDao.insertAll(localCryptoFeedItem)
    }
}