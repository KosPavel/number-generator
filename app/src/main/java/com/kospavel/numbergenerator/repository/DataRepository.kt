package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.SimpleGenerator
import io.reactivex.rxjava3.core.Observable

class DataRepository(type: SequenceType) {

    private var items = mutableListOf<Int>()

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.PRIME -> SimpleGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

    fun loadNext(chunk: Int): Observable<List<Int>> {
        return Observable.fromCallable {
            items = sequenceGenerator.next().toMutableList()
            items
        }
    }

    fun loadPrev(chunk: Int): Observable<List<Int>> {
        return Observable.fromCallable {
            items = sequenceGenerator.prev().toMutableList()
            items
        }
    }

}