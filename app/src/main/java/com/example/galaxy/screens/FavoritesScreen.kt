package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galaxy.R
import com.example.galaxy.models.User
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.views.MovieDetailUiState

@Composable
fun FavoritesScreen (
    favoriteMovies: MutableList<Movie>,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState,
    user: User
) {
    Box {
        Image (
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Home screen background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(36, 54, 82, 220))
                .padding(top = 30.dp, start = 5.dp, end = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Galaxy",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.brunoaceregular)),
                    color = Color(255, 255, 255, 250),
                )
                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(Color(212, 95, 113, 255))
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }

            Text (
                text = "Favorites",
                color = Color(255, 255, 255, 250),
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.quicksandbold)),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )

            LazyVerticalGrid (
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 6.dp, bottom = 68.dp)
            ) {
                itemsIndexed(favoriteMovies.filter { it.addedUserId == user.userId }) { _, movie ->
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
    }
}