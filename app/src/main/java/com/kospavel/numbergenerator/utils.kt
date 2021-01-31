package com.kospavel.numbergenerator

import androidx.annotation.StringRes

enum class SequenceType(@StringRes val nameId: Int) {
    PRIME(R.string.simple_numbers_tab_title),
    FIBONACCI(R.string.fibonacci_numbers_tab_title),
}

data class Number(val value: Int, val white: Boolean) : Content()
class LoadNext : Content()
class LoadPrevious : Content()

open class Content

class ChessColorResolver {
    private var count: Int = 0
    fun isWhite(): Boolean {
        count += 1
        if (count > 2) {
            count = 1
        }
        return count % 2 == 0
    }
}