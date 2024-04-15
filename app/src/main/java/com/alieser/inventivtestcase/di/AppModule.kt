package com.alieser.inventivtestcase.di

import com.alieser.inventivtestcase.api.WalletAPI
import com.alieser.inventivtestcase.constants.Constants
import com.alieser.inventivtestcase.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWalletAPI() : WalletAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.walletAPIBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WalletAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(walletAPI: WalletAPI) : WalletRepository = WalletRepository(walletAPI)
 }