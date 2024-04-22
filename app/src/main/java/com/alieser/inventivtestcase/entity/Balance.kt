package com.alieser.inventivtestcase.entity

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

data class Balance(
    val value: String,
    val currency: String
) {
    override fun toString(): String {

        val formatter = DecimalFormat("#,###,##0.00")
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
