package com.example.presentation.navigation

sealed class Destination(val route: String) {
    data object Home : Destination(HOME_ROUTE)
    data object Contractors : Destination(CONTRACTORS_ROUTE)
    data object Documents : Destination(DOCUMENTS_ROUTE)

    private companion object {
        const val HOME_ROUTE = "HomeRoute"
        const val CONTRACTORS_ROUTE = "ContractorsRoute"
        const val DOCUMENTS_ROUTE = "DocumentsRoute"
    }
}
