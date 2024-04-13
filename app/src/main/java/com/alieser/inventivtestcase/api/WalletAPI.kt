package com.alieser.inventivtestcase.api

import com.alieser.inventivtestcase.entity.WalletResponse
import retrofit2.http.GET

interface WalletAPI {
    @GET("wallets")
    suspend fun getWallet() : WalletResponse
}