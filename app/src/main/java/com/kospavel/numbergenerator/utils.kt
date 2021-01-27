package com.kospavel.numbergenerator

import androidx.annotation.StringRes

enum class SequenceType(@StringRes val nameId: Int) {
    SIMPLE(R.string.simple_numbers_tab_title),
    FIBONACCI(R.string.fibonacci_numbers_tab_title),
}

data class Number(val value: Int) : Content()
class LoadMore : Content()

open class Content