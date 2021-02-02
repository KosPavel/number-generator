package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.PrimeGenerator
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.math.BigInteger

class DataRepository(type: SequenceType) {

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.PRIME -> PrimeGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

    fun loadNext(sequence: List<BigInteger>?, chunk: Int): Observable<List<BigInteger>> {
        return Observable.fromCallable {
            sequenceGenerator.next(sequence, chunk)
        }.subscribeOn(Schedulers.computation())
    }

}