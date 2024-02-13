package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contractors.presentation.ContractorsScreen
import com.example.documents.presentation.DocumentsScreen
import com.example.home.presentation.HomeScreen
import com.example.navigation.Destination.Contractors
import com.example.navigation.Destination.Documents

@Composable
fun navGraphMain() {
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
        composable(Contractors.route) {
            ContractorsScreen()
        }
        composable(Documents.route) { DocumentsScreen() }
    }
}
