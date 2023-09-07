package com.hightech.cryptoapp.frameworks.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hightech.cryptoapp.domain.model.CoinInfoItem
import com.hightech.cryptoapp.domain.model.CryptoFeedItem
import com.hightech.cryptoapp.domain.model.RawItem
import com.hightech.cryptoapp.domain.model.UsdItem

@Entity(tableName = "local_cryptofeed")
data class LocalCryptoFeedItem(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "changepctday") val changePctDay: Float?
) {
    companion object {
        fun List<CryptoFeedItem>.fromDomain(): List<LocalCryptoFeedItem> {
            return this.map {
                LocalCryptoFeedItem(
                    id = it.coinInfo.id,
                    name = it.coinInfo.name,
                    fullName = it.coinInfo.fullName,
                    imageUrl = it.coinInfo.imageUrl,
                    price = it.raw.usd.price,
                    changePctDay = it.raw.usd.changePctDay,
                )
            }
        }

        fun LocalCryptoFeedItem.toDomain(): CryptoFeedItem {
            val coinInfoItem = CoinInfoItem(
                id = this.id,
                name = this.name.orEmpty(),
                fullName = this.fullName.orEmpty(),
                imageUrl = this.imageUrl.orEmpty(),
            )
            val rawItem = RawItem(
                usd = UsdItem(
                    price = this.price ?: 0.0, changePctDay = this.changePctDay ?: 0F
                )
            )
            return CryptoFeedItem(
                coinInfo = coinInfoItem, raw = rawItem
            )
        }
    }
}