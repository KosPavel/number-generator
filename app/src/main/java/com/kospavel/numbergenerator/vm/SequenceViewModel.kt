package com.kospavel.numbergenerator.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kospavel.numbergenerator.*
import com.kospavel.numbergenerator.Number
import com.kospavel.numbergenerator.base.BaseViewModel
import com.kospavel.numbergenerator.repository.DataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class SequenceViewModel(type: SequenceType) : BaseViewModel() {

    private val repo = DataRepository(type)
    private val _items = MutableLiveData<List<Content>>(emptyList())
    val items = _items

    fun loadMore() {
        repo.loadMore().observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            val result = mutableListOf<Content>()
            Log.i("qwerty", it.toString())
            var color = true
            for (i in it) {
                result.add(Number(i, color))
                color = !color
            }
//            result.add(LoadNext())
            _items.value = result
        }.addToDisposable()
    }

    fun loadLess() {
        repo.loadLess().observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            val result = mutableListOf<Content>()
            var color = true
            for (i in it) {
                result.add(Number(i, color))
                color = !color
            }
//            result.add(0, LoadPrevious())
            _items.value = result
        }.addToDisposable()
    }

    init {
        loadMore()
    }

}