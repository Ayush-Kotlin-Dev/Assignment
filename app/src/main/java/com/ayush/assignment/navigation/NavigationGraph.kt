package com.ayush.assignment.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ayush.assignment.presentation.detail.MealDetailScreen
import com.ayush.assignment.presentation.detail.MealDetailViewModel
import com.ayush.assignment.presentation.home.HomeScreen
import com.ayush.assignment.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MealDetail : Screen("meal/{mealId}") {
        fun createRoute(mealId: String) = "meal/$mealId"
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(Screen.Home.route) {
            val viewModel = koinViewModel<HomeViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            HomeScreen(
                uiState = uiState,
                onCategorySelected = viewModel::loadMealsForCategory,
                onRetry = viewModel::retry,
                onMealClick = { mealId ->
                    navController.navigate(Screen.MealDetail.createRoute(mealId))
                }
            )
        }

        composable(
            route = Screen.MealDetail.route,
            arguments = listOf(
                navArgument("mealId") { type = NavType.StringType }
            )
        ) {
            val viewModel = koinViewModel<MealDetailViewModel> {
                parametersOf(it.arguments?.getString("mealId"))
            }
            val uiState by viewModel.uiState.collectAsState()

            MealDetailScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}