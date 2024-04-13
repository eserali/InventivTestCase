package com.alieser.inventivtestcase.repository

import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.api.WalletAPI
import com.alieser.inventivtestcase.api.WalletAPIRetrofitInstance
import com.alieser.inventivtestcase.entity.WalletResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class WalletRepository @Inject constructor(private var walletAPI : WalletAPI) {

    suspend fun getWallet() : Resource<WalletResponse> {
        return try {
            Resource.Success(walletAPI.getWallet())
        } catch (e:Exception) {
            Resource.Error(e.localizedMessage!!)
        }
    }
}