package com.alieser.inventivtestcase.entity

import java.util.Calendar

data class WalletItemResponse(
    val image: String,
    val number: String,
    val cvv: String,
    val balance: Balance,
    val pendingBalance: Balance
)
