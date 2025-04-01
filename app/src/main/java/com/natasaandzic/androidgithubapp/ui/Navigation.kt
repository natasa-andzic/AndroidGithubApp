package com.natasaandzic.androidgithubapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natasaandzic.androidgithubapp.mvvm.GitHubViewModel

@Composable
fun Navigation() {
    val gitHubViewModel: GitHubViewModel = viewModel()
    val viewState by gitHubViewModel.dataState

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "userRepoScreen") {
        composable("userRepoScreen") { UserRepoScreen(navController, viewState) }
        composable(
            route = "repoDetailsScreen/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            RepoDetailsScreen(name)
        }
    }
}