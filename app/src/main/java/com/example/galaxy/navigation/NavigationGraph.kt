package com.example.galaxy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.example.galaxy.models.User
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.screens.*
import com.example.galaxy.views.MoviesPagingViewModel
import com.example.galaxy.views.MoviesViewModel

@Composable
fun NavigationGraph (
    navController: NavHostController,
    moviesViewModel: MoviesViewModel,
    movieDetail: MovieDetail,
    user: User,
    favoriteMovies: MutableList<Movie>,
    moviesPagingViewModel: MoviesPagingViewModel
) {
    NavHost (
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(moviesUiState = moviesViewModel.moviesUiState, moviesViewModel.movieDetailUiState, movieDetail, retryAction = { moviesViewModel::getMoviesList }, navController)
        }
        composable(BottomNavItem.Favorites.route) {
            FavoritesScreen(favoriteMovies, navController, movieDetail, moviesViewModel.movieDetailUiState, user)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(user, navController)
        }
        composable(BottomNavItem.MoviesList.route) {
            MoviesScreen(moviesUiState = moviesViewModel.moviesUiState, moviesViewModel.movieDetailUiState, movieDetail, retryAction = { moviesViewModel::getMoviesList }, navController, moviesPagingViewModel)
        }
        composable(BottomNavItem.Search.route) {
            SearchScreen(moviesUiState = moviesViewModel.moviesUiState, navController, movieDetail, moviesViewModel.movieDetailUiState)
        }
        composable("movie") {
            MovieDetailScreen(user, movieDetail, favoriteMovies, navController)
        }
    }
}