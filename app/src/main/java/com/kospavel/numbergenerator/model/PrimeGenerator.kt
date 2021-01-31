package com.kospavel.numbergenerator.model

import java.math.BigInteger

class PrimeGenerator : Generator {

    override fun next(sequence: List<BigInteger>?, chunk: Int): List<BigInteger> { //todo ready
        return if (sequence != null && sequence.isNotEmpty()) {
            var prime = sequence.last()
            val result = mutableListOf<BigInteger>()
            for (i in 0..chunk) {
                while (true) {
                    prime += 1.toBigInteger()
                    var isPrime = true
                    for (j in 2 until prime.toInt()) {
                        if (prime % j.toBigInteger() == 0.toBigInteger()) {
                            isPrime = false
                            break
                        }
                    }
                    if (isPrime) {
                        result.add(prime)
                    }
                    break
                }
            }
            return result
        } else {
            generateBase()
        }
    }

    override fun prev(sequence: List<BigInteger>?, chunk: Int): List<BigInteger> { //todo ready
        return if (sequence != null) {
            if (sequence[0] == 2.toBigInteger()) {
                return emptyList()
            } else {
                var prime = sequence[0]
                val result = mutableListOf<BigInteger>()
                for (i in 0..chunk) {
                    while (true) {
                        prime -= 1.toBigInteger()
                        var isPrime = true
                        for (j in 2 until prime.toInt()) {
                            if (prime % j.toBigInteger() == 0.toBigInteger()) {
                                isPrime = false
                                break
                            }
                        }
                        if (isPrime) {
                            result.add(prime)
                        }
                        break
                    }
                    if (prime == 2.toBigInteger()) {
                        break
                    }
                }
                return result
            }
        } else {
            generateBase()
        }
    }

    override fun generateBase(): List<BigInteger> {
        return listOf(2).map {
            it.toBigInteger()
        }
    }

    companion object {
        private var instance: PrimeGenerator? = null
        fun get(): PrimeGenerator {
            if (instance == null) {
                instance = PrimeGenerator()
            }
            return instance!!
        }
    }
}