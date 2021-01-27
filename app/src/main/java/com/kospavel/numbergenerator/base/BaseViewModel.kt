package com.kospavel.numbergenerator.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kospavel.numbergenerator.Content
import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.repository.DataRepository

open class BaseViewModel(type: SequenceType) : ViewModel() {

    protected val repo = DataRepository(type)
    protected val _items = MutableLiveData<List<Content>>()
    val items = _items

}