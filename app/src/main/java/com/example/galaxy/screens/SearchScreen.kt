package com.example.galaxy.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavController
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.views.MoviesUiState
import java.util.*
import com.example.galaxy.R
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.views.MovieDetailUiState
import com.example.galaxy.views.MoviesViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun SearchScreen (
    moviesUiState: MoviesUiState,
    navController: NavController,
    movieDetail: MovieDetail,
    moviesDetailUiState: MovieDetailUiState
) {
    var searchText by remember { mutableStateOf("") }
    var moviesList by remember {
        mutableStateOf<MutableList<Movie>>(mutableListOf())
    }

    Box {
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Home screen background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(36, 54, 82, 190))
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

            TextField(
                value = searchText,
                onValueChange = { newValue ->
                    searchText = newValue
                    if(searchText.isNotEmpty()) {
                        moviesList = when(moviesUiState) {
                            is MoviesUiState.Loading -> mutableListOf()
                            is MoviesUiState.Success -> moviesUiState.moviesList.filter { movie ->
                                movie.title?.toLowerCase(Locale.ROOT)!!.contains(searchText.toLowerCase(Locale.ROOT))
                            } as MutableList<Movie>
                            MoviesUiState.Error -> mutableListOf()
                        }
                    } else moviesList.clear()
                },
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .shadow(2.dp)
                    .background(Color(51, 73, 109, 255))
                    .padding(top = 1.dp, start = 5.dp, end = 5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(255, 255, 255, 230),
                    backgroundColor = Color(51, 73, 109, 255),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Search book by title",
                        color = Color(255, 255, 255, 180),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandsemibold))
                    )
                },
                trailingIcon = {
                    Icon (
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 12.dp, bottom = 2.dp),
                        tint = Color(255, 255, 255, 180)
                    )
                },
            )

            SearchMovies(moviesList, navController, movieDetail, moviesDetailUiState)
        }
    }
}