package com.example.improvedmuseumartgallery.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.improvedmuseumartgallery.ui.screens.DetailScreen
import com.example.improvedmuseumartgallery.ui.screens.SearchScreen
import com.example.improvedmuseumartgallery.viewModel.DetailViewModel
import com.example.improvedmuseumartgallery.viewModel.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            val searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
            SearchScreen(searchViewModel) { artId ->
                navController.navigate("detail/$artId")
            }
        }
        composable("detail/{artId}") {backStackEntry ->
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            val artId = backStackEntry.arguments?.getString("artId")?.toInt() ?: 0
            DetailScreen(artId, detailViewModel)
        }
    }
}