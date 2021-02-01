package com.kospavel.numbergenerator.vm

import androidx.lifecycle.LiveData
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
    val items: LiveData<List<Number>> = _items

    fun loadNext() {
        val oldItems = _items.value!!.toMutableList().also {
            if (it.isNotEmpty()) {
                it.last().loadNext = false
            }
        }
        _items.value = oldItems
        repo.loadNext(
            makeSequence(_items.value),
            CHUNK
        ).map {
            val numbersList = it.map { bigInt ->
                Number(bigInt)
            }
            mutableListOf<Number>().apply {
                addAll(oldItems)
                addAll(numbersList)
                last().loadNext = true
            }
        }.map {
            val chessColorResolver = ChessColorResolver()
            for (el in it) {
                el.white = chessColorResolver.isWhite()
            }
            it
        }.observeOn(AndroidSchedulers.mainThread()).subscribeBy {
            _items.value = it
        }.addToDisposable()
    }

    private fun makeSequence(contentList: List<Number>?): List<BigInteger>? {
        return contentList?.map {
            it.value
        }
    }

    init {
        loadNext()
    }

    companion object {
        private const val CHUNK = 50
    }

}