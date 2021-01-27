package com.kospavel.numbergenerator.vm

import androidx.lifecycle.MutableLiveData
import com.kospavel.numbergenerator.Content
import com.kospavel.numbergenerator.Number
import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.base.BaseViewModel
import com.kospavel.numbergenerator.repository.DataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class SequenceViewModel(type: SequenceType) : BaseViewModel() {

    private val repo = DataRepository(type)
    private val _items = MutableLiveData<List<Content>>()
    val items = _items

    init {
        repo.loadMore().observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            val result = mutableListOf<Number>()
            it.map { calculatedNumber ->
                result.add(Number(calculatedNumber))
            }
        }.addToDisposable()
    }

}