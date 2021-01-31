package com.kospavel.numbergenerator

import androidx.annotation.StringRes

enum class SequenceType(@StringRes val nameId: Int) {
    PRIME(R.string.simple_numbers_tab_title),
    FIBONACCI(R.string.fibonacci_numbers_tab_title),
}

data class Number(
    var value: Int,
    var white: Boolean,
    var loadNext: Boolean = false,
    var loadPrev: Boolean = false
) : Content()

//class LoadNext : Content()
//class LoadPrevious : Content()

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

fun MutableList<Content>.addLoads(): MutableList<Content> {
    this.apply {
//        add(0, LoadPrevious())
//        add(LoadNext())
    }
    return this
}