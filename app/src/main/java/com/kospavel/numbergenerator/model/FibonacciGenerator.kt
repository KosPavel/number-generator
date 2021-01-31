package com.kospavel.numbergenerator.model

import java.math.BigInteger

class FibonacciGenerator : Generator {

    override fun next(sequence: List<BigInteger>?, chunk: Int): List<BigInteger> { //todo ready
        return if (sequence != null && sequence.isNotEmpty()) {
            val result = mutableListOf<BigInteger>()
            for (i in 0..chunk) {
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

    override fun prev(sequence: List<BigInteger>?, chunk: Int): List<BigInteger> { //TODO ready
        return if (sequence != null) {
            if (sequence[0] == 1.toBigInteger() && sequence[1] == 1.toBigInteger()) {
                return emptyList()
            } else {
                val result = mutableListOf<BigInteger>()
                for (i in 0..chunk) {
                    when (result.size) {
                        0 -> {
                            result.add(
                                sequence[1] - sequence[0]
                            )
                            if (sequence[0] == 1.toBigInteger() && result[0] == 1.toBigInteger()) {
                                break
                            }
                        }
                        1 -> {
                            result.add(
                                sequence[0] - result[0]
                            )
                            if (result[0] == 1.toBigInteger() && result[1] == 1.toBigInteger()) {
                                break
                            }
                        }
                        else -> {
                            result.add(
                                result.last() + result[result.lastIndex - 1]
                            )
                            if (result.last() == 1.toBigInteger() && result[result.lastIndex - 1] == 1.toBigInteger()) {
                                break
                            }
                        }
                    }
                }
                return result
            }
        } else {
            generateBase()
        }
    }

    override fun generateBase(): List<BigInteger> {
        return listOf(1, 1, 2).map {
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