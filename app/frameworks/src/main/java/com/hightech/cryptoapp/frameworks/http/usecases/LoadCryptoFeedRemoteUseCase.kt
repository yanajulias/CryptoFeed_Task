package com.hightech.cryptoapp.frameworks.http.usecases

import android.util.Log
import com.hightech.cryptoapp.domain.usecases.CryptoFeedLoader
import com.hightech.cryptoapp.domain.usecases.CryptoFeedResult
import com.hightech.cryptoapp.frameworks.http.ConnectivityException
import com.hightech.cryptoapp.frameworks.http.CryptoFeedHttpClient
import com.hightech.cryptoapp.frameworks.http.HttpClientResult
import com.hightech.cryptoapp.frameworks.http.InvalidDataException
import com.hightech.cryptoapp.frameworks.mapper.CryptoFeedItemsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadCryptoFeedRemoteUseCase(
    private val cryptoFeedHttpClient: CryptoFeedHttpClient
) : CryptoFeedLoader {
    override fun load(): Flow<CryptoFeedResult> = flow {
        cryptoFeedHttpClient.get().collect { result ->
            when (result) {
                is HttpClientResult.Success -> {
                    val cryptoFeed = result.root.data
                    if (cryptoFeed.isNotEmpty()) {
                        emit(
                            CryptoFeedResult.Success(
                            CryptoFeedItemsMapper.map(cryptoFeed)))
                    } else {
                        emit(CryptoFeedResult.Success(emptyList()))
                    }
                }

                is HttpClientResult.Failure -> {
                    Log.d("loadCryptoFeed", "Failure")
                    when (result.throwable) {
                        is ConnectivityException -> {
                            emit(
                                CryptoFeedResult.Failure(
                                Connectivity()
                            ))
                        }

                        is InvalidDataException -> {
                            Log.d("loadCryptoFeed", "InvalidData")
                            emit(
                                CryptoFeedResult.Failure(
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