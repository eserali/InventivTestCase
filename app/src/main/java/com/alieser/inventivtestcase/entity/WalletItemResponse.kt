package com.alieser.inventivtestcase.entity

data class WalletItemResponse(
    val image: String,
    val number: String,
    val cvv: String,
    val balance: Balance,
    val pendingBalance: Balance
)
