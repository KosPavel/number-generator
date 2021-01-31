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
        load {
            repo.loadNext(
                makeSequence(_items.value),
                CHUNK
            )
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            val oldList = _items.value?.toMutableList()
            if (oldList != null) {
                val newList = it.toMutableList().apply {
                    addAll(0, oldList)
                    addLoads()
                }
                _items.value = newList
            }
        }.addToDisposable()
    }

    fun loadPrevious() {
        load {
            repo.loadPrev(
                makeSequence(_items.value),
                CHUNK
            )
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            val oldList = _items.value?.toMutableList()
            if (oldList != null) {
                val newList = it.toMutableList().apply {
                    addAll(oldList)
                    addLoads()
                }
                _items.value = newList
            }
        }.addToDisposable()
    }

    private fun load(moreOrLess: () -> Observable<List<Int>>): Observable<List<Content>> {
        return moreOrLess().map<List<Content>> {
            val result = mutableListOf<Content>()
            for (i in it) {
                result.add(Number(i, chessColorResolver.isWhite()))
            }
            result
        }
    }

    private fun makeSequence(contentList: List<Content>?): List<Int>? {
        return contentList?.filterIsInstance<Number>()?.map {
            it.value
        }
    }

    init {
        loadNext()
        Log.i("qwerty", "VM init with type $type")
    }

    companion object {
        private const val MAX_ITEMS = 200
        private const val CHUNK = 50
    }

}