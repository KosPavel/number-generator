package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.Number
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

    fun loadNext(sequence: List<BigInteger>?, chunk: Int): Observable<List<Number>> {
        return load(nextOrPrev = { sequenceGenerator.next(sequence, chunk) }, isNext = true)
    }

    fun loadPrev(sequence: List<BigInteger>?, chunk: Int): Observable<List<Number>> {
        return load(nextOrPrev = { sequenceGenerator.prev(sequence, chunk) }, isNext = false)
    }

    private fun load(
        nextOrPrev: () -> List<BigInteger>,
        isNext: Boolean = true
    ): Observable<List<Number>> {
        return Observable.fromCallable {
            val intList = nextOrPrev()
            val result = if (isNext) {
                intList.map {
                    Number(it)
                }
            } else {
                intList.map {
                    Number(it)
                }.reversed()
            }
            val base = sequenceGenerator.generateBase()
            var setLoadPrev = true
            for (el in base) {
                if (!result.contains(el)) {
                    setLoadPrev = false
                }
            }
//            result[0].loadPrev = !intList.contains(sequenceGenerator.generateBase())
            result[0].loadPrev = setLoadPrev
            result.last().loadNext = true
            result
        }.subscribeOn(Schedulers.computation())
    }

}