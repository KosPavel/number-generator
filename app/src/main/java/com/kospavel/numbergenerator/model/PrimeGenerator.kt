package com.kospavel.numbergenerator.model

class PrimeGenerator : Generator {

    override fun next(seq: List<Int>?, chunk: Int): List<Int> { //todo ready
        return if (seq != null && seq.isNotEmpty()) {
            var prime = seq.last()
            val result = mutableListOf<Int>()
            for (i in 0..chunk) {
                while (true) {
                    prime += 1
                    var isPrime = true
                    for (j in 2 until prime) {
                        if (prime % j == 0) {
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

    override fun prev(sequence: List<Int>?, chunk: Int): List<Int> { //todo ready
        return if (sequence != null) {
            if (sequence[0] == 2) {
                return emptyList()
            } else {
                var prime = sequence[0]
                val result = mutableListOf<Int>()
                for (i in 0..chunk) {
                    while (true) {
                        prime -= 1
                        var isPrime = true
                        for (j in 2 until prime) {
                            if (prime % j == 0) {
                                isPrime = false
                                break
                            }
                        }
                        if (isPrime) {
                            result.add(prime)
                        }
                        break
                    }
                    if (prime == 2) {
                        break
                    }
                }
                return result
            }
        } else {
            generateBase()
        }
    }

    override fun generateBase(): List<Int> {
        val base = mutableListOf(2)
        var prime = 2
        for (i in 0..50) {
            var isPrime = true
            while (true) {
                prime += 1
                for (j in 2 until prime) {
                    if (prime % j == 0) {
                        isPrime = false
                        break
                    }
                }
                break
            }
            if (isPrime) {
                base.add(prime)
            }
        }
        return base
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