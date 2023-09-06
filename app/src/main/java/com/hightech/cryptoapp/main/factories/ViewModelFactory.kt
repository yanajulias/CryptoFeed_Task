package com.hightech.cryptoapp.main.factories

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hightech.cryptoapp.crypto.feed.presentation.CryptoFeedViewModel
import com.hightech.cryptoapp.main.factories.caching.CryptoFeedCompositeFactory
import com.hightech.cryptoapp.main.factories.caching.CryptoFeedDecoratorFactory
import com.hightech.cryptoapp.main.factories.caching.CryptoFeedLocalUseCaseFactory

class ViewModelFactory {
    companion object {
        fun provideViewModel(context: Context): ViewModelProvider.Factory = viewModelFactory {
            val cryptofeedDecorator = CryptoFeedDecoratorFactory.createCryptoFeedCacheDecorator(
                cryptoFeedLoader = CryptoFeedRemoteLoaderFactory.createRemoteCryptoFeedLoader(),
                cache = CryptoFeedLocalUseCaseFactory.createInsertCryptoFeedLocalUseCase(context)
            )

            val cryptoFeedComposite = CryptoFeedCompositeFactory.createCryptoFeedLoaderWithFallback(
                primary = cryptofeedDecorator,
                fallback = CryptoFeedLocalUseCaseFactory.createLoadCryptoFeedLocalUseCase(context)
            )

            initializer {
                CryptoFeedViewModel(cryptoFeedComposite)
            }
        }
    }
}