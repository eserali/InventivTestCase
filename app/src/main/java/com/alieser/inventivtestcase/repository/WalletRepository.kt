package com.alieser.inventivtestcase.repository

import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.api.WalletAPIRetrofitInstance
import com.alieser.inventivtestcase.entity.WalletResponse

class WalletRepository {

    suspend fun getWallet() : Resource<WalletResponse> {
        return try {
            //Resource.Success(walletAPI.getWallet())
            Resource.Success(WalletAPIRetrofitInstance.walletAPI.getWallet())
        } catch (e:Exception) {
            Resource.Error(e.localizedMessage!!)
        }
    }
}