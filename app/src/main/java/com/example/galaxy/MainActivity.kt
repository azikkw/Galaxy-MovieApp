package com.example.galaxy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.galaxy.models.User
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.navigation.BottomNavigation
import com.example.galaxy.navigation.NavigationGraph
import com.example.galaxy.screens.SignUpScreen
import com.example.galaxy.screens.SingInScreen
import com.example.galaxy.screens.WelcomeScreen
import com.example.galaxy.ui.theme.GalaxyTheme
import com.example.galaxy.views.FavoritesViewModel
import com.example.galaxy.views.MoviesPagingViewModel
import com.example.galaxy.views.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = User("", "", "", "")
        val favoritesViewModel: FavoritesViewModel by viewModels()

        setContent {
            GalaxyTheme {
                GalaxyWelcome (
                    user = user,
                    context = applicationContext,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }
}

@Composable
fun GalaxyWelcome (
    user: User,
    context: Context,
    favoritesViewModel: FavoritesViewModel
) {
    val navController = rememberNavController()
    NavHost (
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("sign-up") {
            SignUpScreen(navController, context)
        }
        composable("sign-in") {
            SingInScreen(user, navController, context)
        }
        composable("galaxy") {
            Galaxy(user, favoritesViewModel)
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Galaxy (
    user: User,
    favoritesViewModel: FavoritesViewModel
) {
    val moviesViewModel: MoviesViewModel = viewModel(factory = MoviesViewModel.Factory)
    val moviesPagingViewModel: MoviesPagingViewModel = viewModel(factory = MoviesPagingViewModel.Factory)
    val navController = rememberNavController()

    val movieDetail: MovieDetail = MovieDetail (
        id = 0,
        title ="",
        overview = "",
        backdropPath = "",
        posterPath = "",
        releaseDate = "",
        voteAverage = 0.0,
        voteCount = 0,
        popularity = 0.0,
        budget = 0,
        addedUserId = ""
    )

    var favoriteMovies by remember {
        mutableStateOf<MutableList<Movie>>(mutableListOf())
    }
    favoriteMovies = favoritesViewModel.favoriteMovies

    Scaffold (
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Column (
            modifier = Modifier.padding(it).fillMaxSize()
        ) {}
        NavigationGraph (
            navController = navController,
            moviesViewModel = moviesViewModel,
            movieDetail = movieDetail,
            user = user,
            favoriteMovies = favoriteMovies,
            moviesPagingViewModel = moviesPagingViewModel
        )
    }
}