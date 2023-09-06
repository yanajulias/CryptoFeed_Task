package aej.android.enthusiast.features.cryptofeedlist.presentation

import aej.android.enthusiast.domain.model.CryptoFeedItem
import aej.android.enthusiast.domain.usecases.CryptoFeedLoader
import aej.android.enthusiast.frameworks.http.usecases.Connectivity
import aej.android.enthusiast.frameworks.http.usecases.InvalidData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface CryptoFeedUiState {
    val isLoading: Boolean
    val failed: String

    data class HasCryptoFeed(
        override val isLoading: Boolean,
        val cryptoFeeds: List<CryptoFeedItem>,
        override val failed: String
    ) : CryptoFeedUiState

    data class NoCryptoFeed(
        override val isLoading: Boolean,
        override val failed: String,
    ) : CryptoFeedUiState
}

data class CryptoFeedViewModelState(
    val isLoading: Boolean = false,
    val cryptoFeeds: List<CryptoFeedItem> = emptyList(),
    val failed: String = ""
) {
    fun toCryptoFeedUiState(): CryptoFeedUiState =
        if (cryptoFeeds.isEmpty()) {
            CryptoFeedUiState.NoCryptoFeed(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            CryptoFeedUiState.HasCryptoFeed(
                isLoading = isLoading,
                cryptoFeeds = cryptoFeeds,
                failed = failed
            )
        }
}

class CryptoFeedViewModel (
    private val cryptoFeedLoader: CryptoFeedLoader
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        CryptoFeedViewModelState(
            isLoading = true,
            failed = ""
        )
    )

    val cryptoFeedUiState = viewModelState
        .map(CryptoFeedViewModelState::toCryptoFeedUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toCryptoFeedUiState()
        )

    init {
        loadCryptoFeed()
    }

    fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect { result ->
                Log.d("loadCryptoFeed", "$result")
                viewModelState.update {
                    when (result) {
                        is aej.android.enthusiast.domain.usecases.CryptoFeedResult.Success -> it.copy(
                            cryptoFeeds = result.cryptoFeedItems,
                            isLoading = false
                        )

                        is aej.android.enthusiast.domain.usecases.CryptoFeedResult.Failure -> it.copy(
                            failed = when (result.throwable) {
                                is Connectivity -> "Connectivity"
                                is InvalidData -> "Invalid Data"
                                else -> "Something Went Wrong"
                            },
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}