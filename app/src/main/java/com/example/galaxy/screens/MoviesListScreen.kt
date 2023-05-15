package com.example.galaxy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.galaxy.R
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.views.MovieDetailUiState
import com.example.galaxy.views.MoviesViewModel

@Composable
fun MovieCard (
    movie: Movie,
    gradient: Brush,
    paddingStart: Dp,
    paddingEnd: Dp,
    state: Boolean,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    Button (
        onClick = { movieDetail(moviesDetailUiState, movie, movieDetail, navController) },
        contentPadding = PaddingValues(0.dp),
        modifier = if(state) {
            Modifier
                .padding(paddingStart, paddingEnd, bottom = 15.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .requiredHeight(280.dp)
            } else {
            Modifier
                .padding(paddingStart, paddingEnd, bottom = 15.dp)
                .clip(RoundedCornerShape(12.dp))
                .width(162.dp)
                .requiredHeight(280.dp)
            },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box (
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .clip(RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp))
                    .background(Color(45, 45, 45, 160))
                    .zIndex(3f)
                    .padding(top = 1.dp, start = 12.dp, end = 12.dp, bottom = 4.dp)
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = movie.voteAverage.toString(),
                    color = Color(255, 255, 255, 250),
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    textAlign = TextAlign.Center
                )
            }
            Column (
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxSize()
                    .background(gradient)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                movie.posterPath?.let {
                    AsyncImage (
                        model = "https://image.tmdb.org/t/p/w500/$it",
                        contentDescription = "Poster path",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
                movie.title?.let {
                    Text (
                        text = if(it.length > 28) "${it.substring(0, 25)} ..." else it,
                        color = Color(255, 255, 255, 250),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun MoviesListScreen (
    movies: List<Movie>,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState,
    navController: NavController
) {
    Text (
        text = "The most popular",
        color = Color(255, 255, 255, 250),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.quicksandbold)),
        modifier = Modifier.padding(start = 10.dp)
    )
    TheMostPopularMovies(movies, navController, movieDetail, moviesDetailUiState)

    Text (
        text = "Animation movies",
        color = Color(255, 255, 255, 250),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.quicksandbold)),
        modifier = Modifier.padding(start = 10.dp)
    )
    AnimationGenreMovies(movies, 16, navController, movieDetail, moviesDetailUiState)

    Text (
        text = "Science Fiction movies",
        color = Color(255, 255, 255, 250),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.quicksandbold)),
        modifier = Modifier.padding(start = 10.dp)
    )
    ScienceFictionGenreMovies(movies, 878, navController, movieDetail, moviesDetailUiState)
}

@Composable
fun AllMovies (
    movies: List<Movie>,
    size: Int,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp, bottom = 68.dp)
    ) {
        itemsIndexed(movies.subList(0, size)) { _, movie ->
            MovieCard (
                movie = movie,
                gradient = Brush.linearGradient (
                    colors = listOf(Color(92, 76, 200, 255), Color(29, 116, 216)),
                    tileMode = TileMode.Repeated
                ),
                paddingStart = 5.dp,
                paddingEnd = 5.dp,
                state = true,
                navController = navController,
                movieDetail = movieDetail,
                moviesDetailUiState = moviesDetailUiState
            )
        }
    }
}

@Composable
fun TheMostPopularMovies (
    movies: List<Movie>,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    LazyRow (
        modifier = Modifier.padding(top = 15.dp, bottom = 20.dp)
    ) {
        items(movies.filter { movie -> movie.voteAverage!! > 8.0 }) { movie ->
            MovieCard(
                movie = movie,
                gradient = Brush.linearGradient(
                    colors = listOf(Color(92, 76, 200, 255), Color(29, 116, 216)),
                    tileMode = TileMode.Repeated
                ),
                paddingStart = 10.dp,
                paddingEnd = 0.dp,
                state = false,
                navController = navController,
                movieDetail = movieDetail,
                moviesDetailUiState = moviesDetailUiState
            )
        }
    }
}

@Composable
fun AnimationGenreMovies (
    movies: List<Movie>,
    genreId: Int,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    LazyRow (
        modifier = Modifier.padding(top = 15.dp, bottom = 20.dp)
    ) {
        items(movies.filter { movie -> movie.genreIds.contains(genreId) }) { movie ->
            MovieCard(
                movie = movie,
                gradient = Brush.linearGradient(
                    colors = listOf(Color(0, 228, 140), Color(216, 95, 113, 255)),
                    tileMode = TileMode.Repeated
                ),
                paddingStart = 10.dp,
                paddingEnd = 0.dp,
                state = false,
                navController = navController,
                movieDetail = movieDetail,
                moviesDetailUiState = moviesDetailUiState
            )
        }
    }
}

@Composable
fun ScienceFictionGenreMovies (
    movies: List<Movie>,
    genreId: Int,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    LazyRow (
        modifier = Modifier.padding(top = 15.dp, bottom = 68.dp)
    ) {
        items(movies.filter { movie -> movie.genreIds.contains(genreId) }) { movie ->
            MovieCard(
                movie = movie,
                gradient = Brush.linearGradient(
                    colors = listOf(Color(178, 186, 251), Color(150, 96, 218, 255)),
                    tileMode = TileMode.Repeated
                ),
                paddingStart = 10.dp,
                paddingEnd = 0.dp,
                state = false,
                navController = navController,
                movieDetail = movieDetail,
                moviesDetailUiState = moviesDetailUiState
            )
        }
    }
}

@Composable
fun SearchMovies (
    movies: MutableList<Movie>,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    LazyVerticalGrid (
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp, bottom = 68.dp)
    ) {
        itemsIndexed(movies) { _, movie ->
            MovieCard (
                movie = movie,
                gradient = Brush.linearGradient (
                    colors = listOf(Color(92, 76, 200, 255), Color(29, 116, 216)),
                    tileMode = TileMode.Repeated
                ),
                paddingStart = 5.dp,
                paddingEnd = 5.dp,
                state = true,
                navController = navController,
                movieDetail = movieDetail,
                moviesDetailUiState = moviesDetailUiState
            )
        }
    }
}

fun movieDetail(moviesDetailUiState: MovieDetailUiState, movie: Movie, movieDetail: MovieDetail, navController: NavController) {

    when(moviesDetailUiState) {
        MovieDetailUiState.Error -> println("ERROR")
        MovieDetailUiState.Loading -> println("Loading ...")
        is MovieDetailUiState.Success -> moviesDetailUiState.movieDetail
    }

    movieDetail.id = movie.id
    movieDetail.title = movie.title
    movieDetail.overview = movie.overview
    movieDetail.backdropPath = movie.backdropPath
    movieDetail.posterPath = movie.posterPath
    movieDetail.releaseDate = movie.releaseDate
    movieDetail.voteAverage = movie.voteAverage
    movieDetail.voteCount = movie.voteCount
    movieDetail.popularity = movie.popularity

    navController.navigate("movie")

}