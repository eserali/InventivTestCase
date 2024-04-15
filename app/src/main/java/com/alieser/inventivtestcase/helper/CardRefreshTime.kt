package com.alieser.inventivtestcase.helper


import java.util.Calendar

class CardRefreshTime(
    val cardNumber : String
)
{
    private var isFirstTime : Boolean = true
    private var lastProcessTime : Calendar = Calendar.getInstance()
    var isCardInfoVisible : Boolean = false
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
    fun resetLastProcessTime() : String {
        isFirstTime = true
        return getLastProcessTime()
    }
    fun changeAndGetCardInfo()  : Boolean {
        isCardInfoVisible = !isCardInfoVisible
        return isCardInfoVisible
    }


}