package com.alieser.inventivtestcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alieser.inventivtestcase.Resource
import com.alieser.inventivtestcase.entity.WalletResponse
import com.alieser.inventivtestcase.repository.WalletRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainScreenViewModel : ViewModel() {
    var walletRepository = WalletRepository()
    var refreshTime = MutableLiveData<String>()
    var response = MutableLiveData<Resource<WalletResponse>>()
    var time = Calendar.getInstance()


    init {
        getWallet()
    }

    fun getWallet() {
        viewModelScope.launch() {

            response.value = Resource.Loading()
            var result = withContext(Dispatchers.IO) {
                walletRepository.getWallet()
            }
            when(result) {
                is Resource.Success -> {
                    response.value = Resource.Success(result.data!!)
                    refreshTime.value = getRefreshTime()
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

    fun getRefreshTime() : String {
        val lastProcessTime = Calendar.getInstance()
        val refreshTime = (lastProcessTime.timeInMillis - time.timeInMillis) / 1000
        time = lastProcessTime
        return "$refreshTime seconds ago"
    }




}