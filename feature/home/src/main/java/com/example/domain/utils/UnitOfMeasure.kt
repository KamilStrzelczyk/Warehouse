package com.example.domain.utils

sealed class UnitOfMeasure(val name: String) {
    data object Kg : UnitOfMeasure(KG)
    data object Cm : UnitOfMeasure(CM)
    data object Pieces : UnitOfMeasure(PIECES)
    data object Undefine : UnitOfMeasure(UNDEFINE)

    companion object {
        const val KG = "kg"
        const val CM = "cm"
        const val PIECES = "szt"
        const val UNDEFINE = "undefine"
        fun list() = mutableListOf(Kg, Cm, Pieces)
        fun parse(string: String): UnitOfMeasure =
            when (string) {
                "kg" -> Kg
                "cm" -> Cm
                "pieces" -> Pieces
                else -> Undefine
            }
    }
}
