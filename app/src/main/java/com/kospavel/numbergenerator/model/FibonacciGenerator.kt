package com.kospavel.numbergenerator.model

class FibonacciGenerator : Generator {

    private val numbers = mutableListOf<Int>(1, 1, 2)

    override fun next(): List<Int> {
        for (i in 0..FibonacciGenerator.CHUNK) {
            val maxIndex = numbers.lastIndex
            numbers.add(
                numbers[numbers[maxIndex - 1] + numbers[maxIndex]]
            )
            numbers.removeFirst()
        }
        return numbers
    }

    override fun prev(): List<Int> {
        for (i in 0..FibonacciGenerator.CHUNK) {
            if (numbers.first() == 1 && numbers[1] == 1) {
                break
            }
            numbers.add(
                0,
                numbers[0] + numbers[1]
            )
            numbers.removeLast()
        }
        return numbers
    }

    companion object {
        private const val CHUNK = 50
        private var instance: FibonacciGenerator? = null
        fun get(): FibonacciGenerator {
            if (instance == null) {
                instance = FibonacciGenerator()
            }
            return instance!!
        }
    }
}