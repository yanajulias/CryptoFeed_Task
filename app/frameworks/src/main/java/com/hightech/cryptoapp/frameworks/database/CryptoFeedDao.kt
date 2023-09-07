package com.hightech.cryptoapp.frameworks.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoFeedDao {
    @Upsert
    suspend fun insertAll(localCryptoFeedItem: List<LocalCryptoFeedItem>)

    @Query("SELECT * FROM local_cryptofeed")
    fun getAll(): Flow<List<LocalCryptoFeedItem>>
}