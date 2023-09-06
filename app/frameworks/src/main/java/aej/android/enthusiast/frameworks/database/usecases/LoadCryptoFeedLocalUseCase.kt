package aej.android.enthusiast.frameworks.database.usecases

import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.domain.usecases.CryptoFeedResult
import aej.android.enthusiast.frameworks.database.CryptoFeedDao
import aej.android.enthusiast.frameworks.database.LocalCryptoFeedItem.Companion.toDomain
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