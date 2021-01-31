package com.kospavel.numbergenerator.model

import java.math.BigInteger

interface Generator {

    fun next(sequence: List<BigInteger>?, chunk: Int): List<BigInteger>
    fun prev(sequence: List<BigInteger>?, chunk: Int): List<BigInteger>
    fun generateBase(): List<BigInteger>

}