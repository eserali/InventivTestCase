package com.alieser.inventivtestcase.entity

import java.text.DecimalFormat

data class Balance(
    val value: String,
    val currency: String
) {
    override fun toString(): String {
        val formatter = DecimalFormat("#,###.00")
        return convertCurrency() + formatter.format(value.toInt() / 100.0)
    }
    fun convertCurrency() : String{
        return when(currency) {
            "TL" -> "₺"
            "Euro" -> "€"
            "Dolar" -> "$"
            else -> "₺"
        }
    }
}
