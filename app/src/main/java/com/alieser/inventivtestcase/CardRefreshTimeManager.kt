package com.alieser.inventivtestcase

import com.alieser.inventivtestcase.helper.CardRefreshTime


object CardRefreshTimeManager {

    var cardRefreshTimes = ArrayList<CardRefreshTime>()

    fun setOrGetLastProcessTimeAgo(cardNumber : String) : String {

        val card = getCardRefreshTime(cardNumber)

        return card.getLastProcessTime()
    }

    fun resetCardRefreshTime(cardNumber : String) {
        val card = getCardRefreshTime(cardNumber)
        card.resetLastProcessTime()
    }

    private fun getCardRefreshTime(cardNumber : String) : CardRefreshTime {
        val card = cardRefreshTimes.firstOrNull {
            it.cardNumber == cardNumber
        }

        return if (card == null) {
            val cardRefreshTime = CardRefreshTime(cardNumber)
            cardRefreshTimes.add(cardRefreshTime)
            cardRefreshTime
        } else {
            card
        }
    }



}