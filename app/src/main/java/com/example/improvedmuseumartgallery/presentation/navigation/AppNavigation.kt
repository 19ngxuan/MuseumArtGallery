package com.example.improvedmuseumartgallery.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailScreen
import com.example.improvedmuseumartgallery.presentation.screens.detail.DetailViewModel
import com.example.improvedmuseumartgallery.presentation.screens.download.DownloadScreen
import com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail.FavoriteDetailScreen
import com.example.improvedmuseumartgallery.presentation.screens.favoriteDetail.FavoriteDetailViewModel
import com.example.improvedmuseumartgallery.presentation.screens.favorites.FavoritesScreen
import com.example.improvedmuseumartgallery.presentation.screens.favorites.FavoritesViewModel
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchScreen
import com.example.improvedmuseumartgallery.presentation.screens.search.SearchViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.SearchScreen.route) {
        composable(Route.SearchScreen.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetail = { artId ->
                    navController.navigate(Route.DetailScreen.route + "/" + artId)
                },
                navigateToFavorites = {
                    navController.navigate(Route.FavoritesScreen.route)
                },

                navigateToDownload = {
                    navController.navigate(Route.DownloadScreen.route)}
            )
        }
        composable(Route.FavoritesScreen.route) {
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            FavoritesScreen(
                favoritesViewModel,
                navigateToFavoriteDetail = { artId ->
                    navController.navigate(Route.FavoritesDetailScreen.route + "/" + artId)
                },
                navigateToFavorites = {
                    navController.navigate(Route.FavoritesScreen.route)
                },
                navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                },
                navigateToDownload = {
                    navController.navigate(Route.DownloadScreen.route)}

            )
        }
        composable(Route.DetailScreen.route + "/{artId}") {
            val detailViewModel: DetailViewModel = hiltViewModel()
            DetailScreen(
                detailViewModel,
                navigateToFavorites = {
                    navController.navigate(Route.FavoritesScreen.route)
                },
                navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                },
                navigateToDownload = {
                    navController.navigate(Route.DownloadScreen.route)}
            )
        }
        composable(Route.FavoritesDetailScreen.route + "/{artId}") {
            val favoriteDetailViewModel: FavoriteDetailViewModel = hiltViewModel()
            FavoriteDetailScreen(
                favoriteDetailViewModel,
                navigateToFavorites = {
                    navController.navigate(Route.FavoritesScreen.route)
                },
                navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                },
                navigateToDownload = {
                    navController.navigate(Route.DownloadScreen.route)})
        }
        composable(Route.DownloadScreen.route) {
            DownloadScreen(
                navigateToFavorites = {
                    navController.navigate(Route.FavoritesScreen.route)
                },
                navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                }
            )
        }

    }
}