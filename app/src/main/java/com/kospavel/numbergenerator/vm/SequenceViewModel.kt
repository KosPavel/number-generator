package com.kospavel.numbergenerator.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kospavel.numbergenerator.ChessColorResolver
import com.kospavel.numbergenerator.Number
import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.base.BaseViewModel
import com.kospavel.numbergenerator.repository.DataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.math.BigInteger

class SequenceViewModel(type: SequenceType) : BaseViewModel() {

    private val repo = DataRepository(type)
    private val _items = MutableLiveData<List<Number>>(emptyList())
    val items = _items
    private val chessColorResolver = ChessColorResolver()

//    fun loadNext() {
//        repo.loadNext(
//            makeSequence(_items.value),
//            CHUNK
//        ).observeOn(AndroidSchedulers.mainThread()).subscribeBy {
//            val oldList = _items.value?.toMutableList()
//            if (oldList != null) {
//                val newList = it.toMutableList().apply {
//                    addAll(0, oldList)
//                }
//                _items.value = newList
//            }
//        }.addToDisposable()
//    }

    fun loadNext() {
        repo.loadNext(
            makeSequence(_items.value),
            CHUNK
        ).map {
            val oldList = _items.value?.toMutableList()
            if (oldList != null) {
                val newList = it.toMutableList().apply {
                    addAll(oldList)
                }
                newList
            } else {
                it
            }
        }.map {
            val result = mutableListOf<Number>()
            for (i in it) {
                i.white = true
                result.add(i)
            }
            result
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            _items.value = it
        }.addToDisposable()
    }

    fun loadPrevious() {
        repo.loadPrev(
            makeSequence(_items.value),
            CHUNK
        ).map {
            val oldList = _items.value?.toMutableList()
            if (oldList != null) {
                val newList = it.toMutableList().apply {
                    addAll(oldList)
                }
                newList
            } else {
                it
            }
        }.map {
            val result = mutableListOf<Number>()
            for (i in it) {
                i.white = true
                result.add(i)
            }
            result
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            _items.value = it
        }.addToDisposable()
    }

//    fun loadPrevious() {
//        repo.loadPrev(
//            makeSequence(_items.value),
//            CHUNK
//        )
//            .observeOn(AndroidSchedulers.mainThread()).subscribeBy {
//                val oldList = _items.value?.toMutableList()
//                if (oldList != null) {
//                    val newList = it.toMutableList().apply {
//                        addAll(oldList)
////                    addLoads()
//                    }
//                    _items.value = newList
//                }
//            }.addToDisposable()
//    }

//    private fun load(moreOrLess: () -> Observable<List<Number>>): Observable<List<Number>> {
//        return moreOrLess()
//    }

    private fun makeSequence(contentList: List<Number>?): List<BigInteger>? {
        return contentList?.map {
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