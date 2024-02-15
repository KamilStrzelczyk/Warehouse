package com.example.domain.utils

sealed class UnitOfMeasure(val name: String) {
    data object Kg : UnitOfMeasure(KG)
    data object Cm : UnitOfMeasure(CM)
    data object Pieces : UnitOfMeasure(PIECES)


    companion object {
        const val KG = "kg"
        const val CM = "cm"
        const val PIECES = "pieces"
        fun list() = mutableListOf(Kg, Cm, Pieces)
    }
}
