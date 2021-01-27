package com.kospavel.numbergenerator.model

interface Generator {

    fun next() : List<Int>
    fun prev() : List<Int>

}