package com.kospavel.numbergenerator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kospavel.numbergenerator.SequenceType


class SequenceViewModelFactory(private val type: SequenceType) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return SequenceViewModel(type) as T
        if (modelClass.isAssignableFrom(SequenceViewModel::class.java)) {
            return SequenceViewModel(type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}