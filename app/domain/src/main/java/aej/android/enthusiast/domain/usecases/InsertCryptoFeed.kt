package aej.android.enthusiast.domain.usecases

interface InsertCryptoFeed {
    suspend fun insert(cryptoFeedItems: List<aej.android.enthusiast.domain.model.CryptoFeedItem>)
}