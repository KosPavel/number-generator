package com.kospavel.numbergenerator.model

class SimpleGenerator : Generator {

    private val numbers = mutableListOf<Int>(2)

    override fun next(): List<Int> {
        for (j in 0..CHUNK) {
            var lastSimple = numbers.last()
            while (true) {
                var simple = true
                lastSimple += 1
                for (i in 2 until lastSimple) {
                    if (lastSimple % i == 0 ) {
                        simple = false
                        break
                    }
                }
                if (simple) {
                    numbers.add(lastSimple)
                    numbers.removeFirst()
                    break
                }
            }
        }
        return numbers
    }

    override fun prev(): List<Int> {
        for (j in 0..CHUNK) {
            if (numbers.first() == 2) {
                break
            }
            var firstSimple = numbers.first()
            while (true) {
                if (numbers.first() == 2) {
                    break
                }
                var simple = true
                firstSimple -= 1
                for (i in 2 until firstSimple) {
                    if (firstSimple % i == 0) {
                        simple = false
                        break
                    }
                }
                if (simple) {
                    numbers.add(0, firstSimple)
                    break
                }
            }
        }
        return numbers
    }

    companion object {
        private const val CHUNK = 50
        private var instance: SimpleGenerator? = null
        fun get(): SimpleGenerator {
            if (instance == null) {
                instance = SimpleGenerator()
            }
            return instance!!
        }
    }
}