package com.example.presentation.navigation

sealed class Destination(val route: String) {
    data object Home : Destination(HOME_ROUTE)
    data object Contractors : Destination(CONTRACTORS_ROUTE)
    data object Documents : Destination(DOCUMENTS_ROUTE)
    data class DocumentDetails(val documentId: Long) : Destination(buildNavDestination(documentId)) {
        companion object {
            const val ROUTE = DOCUMENTS_DETAILS_ROUTE
            const val DOCUMENT_ID_ARGUMENT = "documentId"
            fun buildNavDestination(documentId: Long) = "DocumentsRoute/$documentId"
        }
    }

    private companion object {
        const val HOME_ROUTE = "HomeRoute"
        const val CONTRACTORS_ROUTE = "ContractorsRoute"
        const val DOCUMENTS_ROUTE = "DocumentsRoute"
        const val DOCUMENTS_DETAILS_ROUTE = "DocumentsRoute/{documentId}"
    }
}
