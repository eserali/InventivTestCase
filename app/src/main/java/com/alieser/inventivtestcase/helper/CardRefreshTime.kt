package com.alieser.inventivtestcase.helper

import java.util.Calendar

class CardRefreshTime(
    val cardNumber : String, // card no boş gelirse exp. fırlat
)
{
    private var isFirstTime : Boolean = true
    private var lastProcessTime : Calendar = Calendar.getInstance()

    fun getLastProcessTime() : String {

        if (isFirstTime) {
            isFirstTime = false
            lastProcessTime = Calendar.getInstance()
            return "0 seconds ago"
        }

        val currentTime = Calendar.getInstance()
        val refreshTime = (currentTime.timeInMillis - lastProcessTime.timeInMillis) / 1000
        val minuteTimeValue = (refreshTime.toInt() / 60)
        val hourTimeValue = (minuteTimeValue / 60)
        if (hourTimeValue > 0) {
            return "$hourTimeValue hours ago"
        }
        if (minuteTimeValue > 0) {
            return "$minuteTimeValue minutes ago"
        }
        return "$refreshTime seconds ago"
    }

    fun resetLastProcessTime() {
        lastProcessTime = Calendar.getInstance()
    }
}