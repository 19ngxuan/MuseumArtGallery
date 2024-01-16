package com.example.improvedmuseumartgallery.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailScreen
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailViewModel
import com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail.FavoriteDetailScreen
import com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail.FavoriteDetailViewModel
import com.example.improvedmuseumartgallery.presentation.screens.favorites.FavoritesScreen
import com.example.improvedmuseumartgallery.presentation.screens.favorites.FavoritesViewModel
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchScreen
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetail = { artId ->
                    navController.navigate("detail/$artId")
                },
                navigateToFavorites = {
                    navController.navigate("favorites")
                }
            )
        }
        composable("favorites") {
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            FavoritesScreen(
                favoritesViewModel,
                navigateToFavoriteDetail = { artId ->
                    navController.navigate("favoriteDetail/$artId")
                },
                navigateToFavorites = {
                    navController.navigate("favorites")
                },
                navigateToSearch = {
                    navController.navigate("search")
                }
            )
        }
        composable("detail/{artId}") {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailScreen(
                detailViewModel,
                navigateToFavorites = {
                    navController.navigate("favorites")
                },
                navigateToSearch = {
                    navController.navigate("search")
                })
        }
        composable("favoriteDetail/{artId}") {
            val favoriteDetailViewModel: FavoriteDetailViewModel = hiltViewModel()
            FavoriteDetailScreen(
                favoriteDetailViewModel,
                navigateToFavorites = {
                    navController.navigate("favorites")
                },
                navigateToSearch = {
                    navController.navigate("search")
                })
        }

    }
}