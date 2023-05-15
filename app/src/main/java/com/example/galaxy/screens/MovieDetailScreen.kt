package com.example.galaxy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.galaxy.R
import com.example.galaxy.models.User
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.navigation.BottomNavItem
import com.example.galaxy.views.MovieDetailUiState
import com.example.galaxy.views.MoviesViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun MovieDetailScreen (
    user: User,
    movieDetail: MovieDetail,
    favoriteMovies: MutableList<Movie>,
    navController: NavController
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(36, 54, 82))
    ) {
        AsyncImage (
            model = "https://image.tmdb.org/t/p/w400/${movieDetail.backdropPath}",
            contentDescription = "Poster path",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
        )

        Box (
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp)
                .zIndex(3f)
                .align(Alignment.TopEnd)
        ) {
            if(favoriteMovies.contains(Movie(movieDetail.id, movieDetail.title, movieDetail.overview, movieDetail.backdropPath, movieDetail.posterPath, movieDetail.releaseDate, movieDetail.voteAverage, movieDetail.voteCount, movieDetail.popularity, arrayListOf(), user.userId))) {
                IconButton (
                    onClick = { removeFromFavorites(user, movieDetail, favoriteMovies, navController) },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(Color(212, 95, 113, 255))
                ) {
                    Icon (
                        Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            } else {
                IconButton (
                    onClick = { addToFavorites(user, movieDetail, favoriteMovies, navController) },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(Color(212, 95, 113, 255))
                ) {
                    Icon (
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(36, 54, 82, 120))
                .padding(start = 20.dp, end = 20.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage (
                model = "https://image.tmdb.org/t/p/w500/${movieDetail.posterPath}",
                contentDescription = "Poster path",
                modifier = Modifier
                    .padding(top = 85.dp, bottom = 20.dp)
                    .shadow(elevation = 10.dp)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                movieDetail.title?.let {
                    Text(
                        text = it,
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        color = Color(255, 255, 255, 250),
                        textAlign = TextAlign.Center
                    )
                }

                Row (
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = movieDetail.voteAverage.toString(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        color = Color(0, 228, 140),
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = "${movieDetail.voteCount.toString()} votes",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                        color = Color(255, 255, 255, 200),
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = "${movieDetail.popularity.toString()} popularity",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                        color = Color(255, 255, 255, 200)
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 5.dp, bottom = 18.dp)
                ) {
                    Text(
                        text = "Released date: ${movieDetail.releaseDate}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                        color = Color(218, 192, 192, 180)
                    )
                }

                movieDetail.overview?.let {
                    Text (
                        text = it,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold)),
                        color = Color(255, 255, 255, 140),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

fun addToFavorites(user: User, movieDetail: MovieDetail, favoriteMovies: MutableList<Movie>, navController: NavController) {

    val movie = hashMapOf (
        "movieId" to movieDetail.id,
        "title" to movieDetail.title,
        "overview" to movieDetail.overview,
        "backdropPath" to movieDetail.backdropPath,
        "posterPath" to movieDetail.posterPath,
        "releaseDate" to movieDetail.releaseDate,
        "voteAverage" to movieDetail.voteAverage,
        "voteCount" to movieDetail.voteCount,
        "popularity" to movieDetail.popularity,
        "genres" to arrayListOf<Int>(),
        "addedUserId" to user.userId
    )

    Firebase.firestore.collection("favorites")
        .document(movieDetail.id.toString() + user.userId).set(movie)
        .addOnSuccessListener {
            favoriteMovies.add (
                Movie (
                    movieDetail.id,
                    movieDetail.title,
                    movieDetail.overview,
                    movieDetail.backdropPath,
                    movieDetail.posterPath,
                    movieDetail.releaseDate,
                    movieDetail.voteAverage,
                    movieDetail.voteCount,
                    movieDetail.popularity,
                    arrayListOf(),
                    user.userId
                )
            )
            navController.navigate("movie")
        }

}

fun removeFromFavorites(user: User, movieDetail: MovieDetail, favoriteMovies: MutableList<Movie>, navController: NavController) {

    Firebase.firestore.collection("favorites")
        .document(movieDetail.id.toString() + user.userId)
        .delete()
        .addOnSuccessListener {
            favoriteMovies.remove(Movie(movieDetail.id, movieDetail.title, movieDetail.overview, movieDetail.backdropPath, movieDetail.posterPath, movieDetail.releaseDate, movieDetail.voteAverage, movieDetail.voteCount, movieDetail.popularity, arrayListOf(), user.userId))
            navController.navigate("movie")
        }

}