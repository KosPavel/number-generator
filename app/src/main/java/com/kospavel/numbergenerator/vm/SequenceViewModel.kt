package com.kospavel.numbergenerator.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kospavel.numbergenerator.*
import com.kospavel.numbergenerator.Number
import com.kospavel.numbergenerator.base.BaseViewModel
import com.kospavel.numbergenerator.repository.DataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy

class SequenceViewModel(type: SequenceType) : BaseViewModel() {

    private val repo = DataRepository(type)
    private val _items = MutableLiveData<List<Content>>(emptyList())
    val items = _items
    private val chessColorResolver = ChessColorResolver()

    fun loadNext() {
        load { repo.loadMore() }
    }

    fun loadPrevious() {
        load { repo.loadLess() }
    }

    private fun load(moreOrLess: () -> Observable<List<Int>>) {
        moreOrLess().map<List<Content>> {
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

    init {
        loadNext()
        Log.i("qwerty", "VM init with type $type")
    }

}