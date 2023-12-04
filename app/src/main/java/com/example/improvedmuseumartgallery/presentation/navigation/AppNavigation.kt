package com.example.improvedmuseumartgallery.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailScreen
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailViewModel
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchScreen
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(searchViewModel) { artId ->
                navController.navigate("detail/$artId")
            }
        }
        composable("detail/{artId}") { backStackEntry ->
            val detailViewModel: DetailViewModel =  hiltViewModel()
            DetailScreen(detailViewModel)
        }
    }
}