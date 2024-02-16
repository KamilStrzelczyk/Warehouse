package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.Destination.Contractors
import com.example.presentation.navigation.Destination.DocumentDetails
import com.example.presentation.navigation.Destination.Documents
import com.example.presentation.navigation.Destination.GoodsDetails
import com.example.presentation.screen.contractors.presentation.ContractorsScreen
import com.example.presentation.screen.documentdetails.DocumentDetailsScreen
import com.example.presentation.screen.documents.presentation.DocumentsScreen
import com.example.presentation.screen.goodsDetails.GoodsDetailsScreen
import com.example.presentation.screen.home.presentation.HomeScreen

@Composable
fun NavGraphMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onFirstButtonClicked = {
                    navController.navigate(
                        Contractors.route
                    )
                },
                onSecondButtonClicked = {
                    navController.navigate(
                        Documents.route
                    )
                }
            )
        }
        composable(Contractors.route) { ContractorsScreen() }
        composable(Documents.route) {
            DocumentsScreen(
                navigateToDocumentDetails = {
                    navController.navigate(DocumentDetails(it).route)
                }
            )
        }
        composable(DocumentDetails.ROUTE) {
            DocumentDetailsScreen(
                navigateToGoodsDetails = { goodsId, documentId ->
                    navController.navigate(GoodsDetails(goodsId, documentId).route)
                }
            )
        }
        composable(GoodsDetails.ROUTE) {
            GoodsDetailsScreen {
                navController.popBackStack()
            }
        }
    }
}
