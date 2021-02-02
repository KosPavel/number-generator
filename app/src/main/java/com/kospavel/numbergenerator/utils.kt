package com.kospavel.numbergenerator

import androidx.annotation.StringRes
import java.math.BigInteger

enum class SequenceType(@StringRes val nameId: Int) {
    PRIME(R.string.simple_numbers_tab_title),
    FIBONACCI(R.string.fibonacci_numbers_tab_title),
}

data class Number(
    var value: BigInteger,
    var white: Boolean? = true,
    var loadNext: Boolean = false
)

class ChessColorResolver {
    private var count: Int = 1
    private var paint = true
    fun isWhite(): Boolean {
        count += 1
        if (count > 2) {
            paint = !paint
            count = 1
        }
        return paint
    }
}