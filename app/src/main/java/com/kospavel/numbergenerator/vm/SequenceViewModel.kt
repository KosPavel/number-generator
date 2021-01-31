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
    private val chessColorResolver = ChessColorResolver()

    fun loadMore() {
        repo.loadMore().map<List<Content>> {
            val result = mutableListOf<Content>()
            for (i in it) {
                result.add(Number(i, chessColorResolver.isWhite()))
            }
            result.add(LoadNext())
            result
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            _items.value = it
        }.addToDisposable()
    }

    fun loadLess() {
        repo.loadLess().map<List<Content>> {
            val result = mutableListOf<Content>()
            var color = true
            for (i in it) {
                result.add(Number(i, color))
                color = !color
            }
            result.add(0, LoadPrevious())
            result
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            _items.value = it
        }.addToDisposable()
    }

    init {
        loadMore()
        Log.i("qwerty", "VM init with type $type")
    }

}