package com.alieser.inventivtestcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.entity.WalletResponse
import com.alieser.inventivtestcase.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private var walletRepository : WalletRepository) : ViewModel() {
    var response = MutableLiveData<Resource<WalletResponse>>()

    init {
        getWallets()
    }
    fun getWallets() {
        viewModelScope.launch() {
            response.value = Resource.Loading()
            val result = withContext(Dispatchers.IO) {
                walletRepository.getWallet()
            }
            when(result) {
                is Resource.Success -> {
                    response.value = Resource.Success(result.data!!)
                }
                is Resource.Error -> {
                    response.value = Resource.Error(result.message.toString())
                }
                else -> {
                    response.value = Resource.Error("Network connection not found")
                }
            }
        }
    }

}