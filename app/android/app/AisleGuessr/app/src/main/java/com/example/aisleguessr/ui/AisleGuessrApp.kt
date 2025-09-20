package com.example.aisleguessr.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aisleguessr.ui.screen.DisclaimerScreen
import com.example.aisleguessr.ui.screen.MainMenuScreen
import com.example.aisleguessr.viewmodel.MainMenuViewModel
import com.example.aisleguessr.navigation.NavigationRoutes
import com.example.aisleguessr.ui.screen.GameScreen
import com.example.aisleguessr.ui.screen.GlossaryScreen

@Composable
fun AisleGuessrApp(mainMenuViewModel: MainMenuViewModel = viewModel()){
    val navController = rememberNavController()

    LaunchedEffect(Unit){
        mainMenuViewModel.navigateTo.collect { dest ->
            navController.navigate(dest)
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.MAIN_MENU){
        composable(NavigationRoutes.MAIN_MENU){
            MainMenuScreen(
                viewModel = mainMenuViewModel
            )
        }
        composable(NavigationRoutes.DISCLAIMER){
            DisclaimerScreen(
                onBackClicked = { navController.popBackStack()}
            )
        }

        composable(NavigationRoutes.PLAY){
            GameScreen(
                onBackClicked = { navController.popBackStack()}
            )
        }

        composable(NavigationRoutes.GLOSSARY){
            GlossaryScreen(
                onBackClicked = { navController.popBackStack()}
            )
        }
    }
}