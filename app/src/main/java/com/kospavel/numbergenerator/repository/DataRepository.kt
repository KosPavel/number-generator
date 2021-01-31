package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.PrimeGenerator
import io.reactivex.rxjava3.core.Observable

class DataRepository(type: SequenceType) {

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.PRIME -> PrimeGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

    fun loadNext(sequence: List<Int>?, chunk: Int): Observable<List<Int>> {
        return Observable.fromCallable {
            sequenceGenerator.next(sequence, chunk).toMutableList()
        }
    }

    fun loadPrev(sequence: List<Int>?, chunk: Int): Observable<List<Int>> {
        return Observable.fromCallable {
            val result = sequenceGenerator.prev(sequence, chunk)
            result.reversed()
        }
    }

}