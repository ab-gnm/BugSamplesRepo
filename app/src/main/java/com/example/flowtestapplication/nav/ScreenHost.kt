package com.example.flowtestapplication.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowtestapplication.screen1.Screen1
import com.example.flowtestapplication.screen2.Screen2

@Composable
fun ScreenHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "screen-1",
        enterTransition = {
            when {
                isGoingFromOneToTwo -> slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                isGoingFromTwoToOne -> slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
                else -> fadeIn()
            }
        },
//        exitTransition = {
//            when {
//                isGoingFromOneToTwo -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
//                isGoingFromTwoToOne -> slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
//                else -> fadeOut()
//            }
//        },
    ) {
        composable("screen-1") {
            val root = remember(it) {
                navController.getBackStackEntry(navController.graph.id)
            }
            Screen1(
                viewModel = viewModel(root),
                onClick = { navController.navigate("screen-2") },
            )
        }
        composable("screen-2") {
            val root = remember(it) {
                navController.getBackStackEntry(navController.graph.id)
            }
            Screen2(
                viewModel = viewModel(root),
                onClick = { navController.navigate("screen-1") },
            )
        }
    }
}

val AnimatedContentTransitionScope<NavBackStackEntry>.isGoingFromOneToTwo: Boolean
    get() = initialState.destination.route == "screen-1" &&
            targetState.destination.route == "screen-2"

val AnimatedContentTransitionScope<NavBackStackEntry>.isGoingFromTwoToOne: Boolean
    get() = initialState.destination.route == "screen-2" &&
            targetState.destination.route == "screen-1"