package com.alieser.inventivtestcase.api

import androidx.compose.ui.unit.Constraints
import com.alieser.inventivtestcase.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WalletAPIRetrofitInstance {

    val walletAPI = Retrofit.Builder()
        .baseUrl(Constants.walletAPIBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WalletAPI::class.java)
}