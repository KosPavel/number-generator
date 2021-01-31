package com.kospavel.numbergenerator.model

class FibonacciGenerator : Generator {

    override fun next(sequence: List<Int>?, chunk: Int): List<Int> { //todo ready
        return if (sequence != null && sequence.isNotEmpty()) {
            val result = mutableListOf<Int>()
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

    override fun prev(sequence: List<Int>?, chunk: Int): List<Int> { //TODO ready
        return if (sequence != null) {
            if (sequence[0] == 1 && sequence[1] == 1) {
                return emptyList()
            } else {
                val result = mutableListOf<Int>()
                for (i in 0..chunk) {
                    when (result.size) {
                        0 -> {
                            result.add(
                                sequence[1] - sequence[0]
                            )
                            if (sequence[0] == 1 && result[0] == 1) {
                                break
                            }
                        }
                        1 -> {
                            result.add(
                                sequence[0] - result[0]
                            )
                            if (result[0] == 1 && result[1] == 1) {
                                break
                            }
                        }
                        else -> {
                            result.add(
                                result.last() + result[result.lastIndex - 1]
                            )
                            if (result.last() == 1 && result[result.lastIndex - 1] == 1) {
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

    override fun generateBase(): List<Int> {
        val base = mutableListOf(1, 1, 2)
        for (i in 0..50) {
            base.add(
                base.last() + base[base.lastIndex - 1]
            )
        }
        return base
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