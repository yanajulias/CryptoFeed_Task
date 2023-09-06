package com.hightech.cryptoapp.crypto.feed.domain

import com.hightech.cryptoapp.crypto.feed.domain.model.CoinInfoItem
import com.hightech.cryptoapp.crypto.feed.domain.model.CryptoFeedItem
import com.hightech.cryptoapp.crypto.feed.domain.model.RawItem
import com.hightech.cryptoapp.crypto.feed.domain.model.UsdItem
import com.hightech.cryptoapp.crypto.feed.http.RemoteCryptoFeedItem

class CryptoFeedItemsMapper {
    companion object {
        fun map(items: List<RemoteCryptoFeedItem>): List<CryptoFeedItem> {
            return items.map {
                CryptoFeedItem(
                    coinInfo = CoinInfoItem(
                        it.remoteCoinInfo.id.orEmpty(),
                        it.remoteCoinInfo.name.orEmpty(),
                        it.remoteCoinInfo.fullName.orEmpty(),
                        it.remoteCoinInfo.imageUrl.orEmpty()
                    ),
                    raw = RawItem(
                        usd = UsdItem(
                            it.remoteRaw.usd.price ?: 0.0,
                            it.remoteRaw.usd.changePctDay ?: 0F
                        )
                    )
                )
            }
        }
    }
}