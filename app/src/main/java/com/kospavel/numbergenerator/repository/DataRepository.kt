package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.SimpleGenerator
import io.reactivex.rxjava3.core.Observable

class DataRepository(type: SequenceType) {

//    private val items

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.PRIME -> SimpleGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

    fun loadMore(): Observable<List<Int>> {
        return Observable.fromCallable {
            sequenceGenerator.next()
        }
    }

    fun loadLess(): Observable<List<Int>> {
        return Observable.fromCallable {
            sequenceGenerator.prev()
        }
    }

}