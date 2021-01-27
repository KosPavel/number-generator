package com.kospavel.numbergenerator.ui

import androidx.annotation.StringRes
import com.kospavel.numbergenerator.R

enum class SequenceType(@StringRes val nameId: Int) {
    SIMPLE(R.string.simple_numbers_tab_title),
    FIBONACCI(R.string.fibonacci_numbers_tab_title),
}