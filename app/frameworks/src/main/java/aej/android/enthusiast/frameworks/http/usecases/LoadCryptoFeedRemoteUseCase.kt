package aej.android.enthusiast.frameworks.http.usecases

import android.util.Log
import aej.android.enthusiast.frameworks.http.ConnectivityException
import aej.android.enthusiast.frameworks.http.CryptoFeedHttpClient
import aej.android.enthusiast.frameworks.http.HttpClientResult
import aej.android.enthusiast.frameworks.http.InvalidDataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadCryptoFeedRemoteUseCase(
    private val cryptoFeedHttpClient: CryptoFeedHttpClient
) : aej.android.enthusiast.domain.usecases.CryptoFeedLoader {
    override fun load(): Flow<aej.android.enthusiast.domain.usecases.CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            when (result) {
                is HttpClientResult.Success -> {
                    val cryptoFeed = result.root.data
                    if (cryptoFeed.isNotEmpty()) {
                        emit(aej.android.enthusiast.domain.usecases.CryptoFeedResult.Success(aej.android.enthusiast.domain.mapper.CryptoFeedItemsMapper.map(cryptoFeed)))
                    } else {
                        emit(aej.android.enthusiast.domain.usecases.CryptoFeedResult.Success(emptyList()))
                    }
                }

                is HttpClientResult.Failure -> {
                    Log.d("loadCryptoFeed", "Failure")
                    when (result.throwable) {
                        is ConnectivityException -> {
                            emit(aej.android.enthusiast.domain.usecases.CryptoFeedResult.Failure(
                                Connectivity()
                            ))
                        }

                        is InvalidDataException -> {
                            Log.d("loadCryptoFeed", "InvalidData")
                            emit(aej.android.enthusiast.domain.usecases.CryptoFeedResult.Failure(
                                InvalidData()
                            ))
                        }
                    }
                }
            }
        }
    }
}

class InvalidData : Throwable()
class Connectivity : Throwable()