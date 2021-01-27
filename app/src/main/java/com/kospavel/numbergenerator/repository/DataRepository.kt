package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.SimpleGenerator
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe

class DataRepository(type: SequenceType) {

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.SIMPLE -> SimpleGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

    fun loadMore(): Observable<List<Int>> {
        return Observable.create(ObservableOnSubscribe {
            it.onNext(sequenceGenerator.next())
            it.onComplete()
        })
    }

    fun loadLess(): Observable<List<Int>> {
        return Observable.create(ObservableOnSubscribe {
            it.onNext(sequenceGenerator.prev())
            it.onComplete()
        })
    }

}