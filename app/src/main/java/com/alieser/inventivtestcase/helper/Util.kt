package com.alieser.inventivtestcase.helper

object Util {
    fun String.toMasked(showDigitCount : Int,chunkSize : Int = 0,separator : String = " "): String {
        val maskString = this.dropLast(showDigitCount).map { '⁕' }.joinToString( "")
        var result = maskString + this.takeLast(showDigitCount)

        if (chunkSize > 0) {
            result = result.chunked(chunkSize).joinToString(separator = separator)
        }
        return result

    }
    fun String.toSplit(chunkSize : Int = 0,separator : String = " "): String {
        return this.chunked(chunkSize).joinToString(separator = separator)
    }

}
