package aej.android.enthusiast.domain.mapper

import aej.android.enthusiast.frameworks.http.RemoteCryptoFeedItem

class CryptoFeedItemsMapper {
    companion object {
        fun map(items: List<RemoteCryptoFeedItem>): List<aej.android.enthusiast.domain.model.CryptoFeedItem> {
            return items.map {
                aej.android.enthusiast.domain.model.CryptoFeedItem(
                    coinInfo = aej.android.enthusiast.domain.model.CoinInfoItem(
                        it.remoteCoinInfo.id.orEmpty(),
                        it.remoteCoinInfo.name.orEmpty(),
                        it.remoteCoinInfo.fullName.orEmpty(),
                        it.remoteCoinInfo.imageUrl.orEmpty()
                    ),
                    raw = aej.android.enthusiast.domain.model.RawItem(
                        usd = aej.android.enthusiast.domain.model.UsdItem(
                            it.remoteRaw.usd.price ?: 0.0,
                            it.remoteRaw.usd.changePctDay ?: 0F
                        )
                    )
                )
            }
        }
    }
}