package com.kospavel.numbergenerator.repository

import com.kospavel.numbergenerator.SequenceType
import com.kospavel.numbergenerator.model.FibonacciGenerator
import com.kospavel.numbergenerator.model.Generator
import com.kospavel.numbergenerator.model.SimpleGenerator

class DataRepository(type: SequenceType) {

    private val sequenceGenerator: Generator = when (type) {
        SequenceType.SIMPLE -> SimpleGenerator.get()
        SequenceType.FIBONACCI -> FibonacciGenerator.get()
    }

}