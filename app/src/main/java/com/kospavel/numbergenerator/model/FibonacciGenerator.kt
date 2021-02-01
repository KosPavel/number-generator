package com.kospavel.numbergenerator.model

import java.math.BigInteger

class FibonacciGenerator : Generator {

    override fun next(sequence: List<BigInteger>?, chunk: Int): List<BigInteger> { //todo ready
        return if (sequence != null && sequence.isNotEmpty()) {
            val result = mutableListOf<BigInteger>()
            for (i in 1..chunk) {
                when (result.size) {
                    0 -> {
                        result.add(sequence.last() + sequence[sequence.lastIndex - 1])
                    }
                    1 -> {
                        result.add(sequence.last() + result.last())
                    }
                    else -> {
                        result.add(
                            result.last() + result[result.lastIndex - 1]
                        )
                    }
                }
            }
            return result
        } else {
            generateBase()
        }
    }

    override fun generateBase(): List<BigInteger> {
        return listOf(1, 1, 2, 3).map {
            it.toBigInteger()
        }
    }

    companion object {
        private var instance: FibonacciGenerator? = null
        fun get(): FibonacciGenerator {
            if (instance == null) {
                instance = FibonacciGenerator()
            }
            return instance!!
        }
    }

}