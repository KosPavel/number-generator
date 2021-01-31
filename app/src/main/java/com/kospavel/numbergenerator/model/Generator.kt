package com.kospavel.numbergenerator.model

interface Generator {

    fun next(sequence: List<Int>?, chunk: Int): List<Int>
    fun prev(sequence: List<Int>?, chunk: Int): List<Int>
    fun generateBase(): List<Int>

}