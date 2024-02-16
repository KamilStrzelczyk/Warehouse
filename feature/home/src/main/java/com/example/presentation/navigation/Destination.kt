package com.example.presentation.navigation

sealed class Destination(val route: String) {
    data object Home : Destination(HOME_ROUTE)
    data object Contractors : Destination(CONTRACTORS_ROUTE)
    data object Documents : Destination(DOCUMENTS_ROUTE)
    data class DocumentDetails(val documentId: Long) : Destination(buildNavDestination(documentId)) {
        companion object {
            const val ROUTE = DOCUMENTS_DETAILS_ROUTE
            const val DOCUMENT_DETAILS_ID_ARGUMENT = "documentId"
            fun buildNavDestination(documentId: Long) = "DocumentsRoute/$documentId"
        }
    }

    data class GoodsDetails(
        val goodsId: Long,
        val documentId: Long,
    ) : Destination(
        buildNavDestination(
            goodsId = goodsId,
            documentId = documentId
        ),
    ) {
        companion object {
            const val ROUTE = GOODS_DETAILS_ROUTE
            const val GOODS_ID_ARGUMENT = "goodsId"
            const val GOODS_DOCUMENT_ID_ARGUMENT = "documentId"
            fun buildNavDestination(
                goodsId: Long,
                documentId: Long,
            ) = "GoodsRoute/$goodsId/$documentId"
        }
    }

    private companion object {
        const val HOME_ROUTE = "HomeRoute"
        const val CONTRACTORS_ROUTE = "ContractorsRoute"
        const val DOCUMENTS_ROUTE = "DocumentsRoute"
        const val DOCUMENTS_DETAILS_ROUTE = "DocumentsRoute/{documentId}"
        const val GOODS_DETAILS_ROUTE = "GoodsRoute/{goodsId}/{documentId}"
    }
}
