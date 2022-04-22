package com.example.petwatcher.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.example.petwatcher.core.presentation.Screen
import com.example.petwatcher.features.auth.presentation.login.LoginScreen
import com.example.petwatcher.features.auth.presentation.register.RegisterScreen
import com.example.petwatcher.features.auth.presentation.splash.SplashScreen
import com.example.petwatcher.features.map.presentation.MapScreen
import com.example.petwatcher.features.profile.presentation.ProfileScreen

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = Screen.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = Screen.MainScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onNavigate = navController::navigate,
                navController = navController,
                scaffoldState = scaffoldState,
                onPopBackStack = navController::popBackStack
            )
        }
        composable(Screen.MainScreen.route) {
            MapScreen(
                onNavigate = navController::navigate,
            )
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                onNavigateUp = navController::navigateUp
            )
        }
    }
}