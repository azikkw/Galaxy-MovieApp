package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galaxy.R
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.navigation.BottomNavItem
import com.example.galaxy.views.MovieDetailUiState
import com.example.galaxy.views.MoviesUiState
import com.example.galaxy.views.MoviesViewModel

@Composable
fun HomeScreen (
    moviesUiState: MoviesUiState,
    moviesDetailUiState: MovieDetailUiState,
    movieDetail: MovieDetail,
    retryAction: () -> Unit,
    navController: NavController
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
                .background(Color(36, 54, 82, 190))
                .padding(top = 30.dp, start = 5.dp, end = 5.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row (
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text (
                    text = "Galaxy",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.brunoaceregular)),
                    color = Color(255, 255, 255, 250),
                )
                IconButton (
                    onClick = { navController.navigate(BottomNavItem.Search.route) },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(Color(212, 95, 113, 255))
                ) {
                    Icon (
                        Icons.Default.Search,
                        contentDescription = "Search",
                        Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }
            when(moviesUiState) {
                is MoviesUiState.Loading -> LoadingScreen()
                is MoviesUiState.Success -> MoviesListScreen(moviesUiState.moviesList, movieDetail, moviesDetailUiState, navController)
                MoviesUiState.Error -> ErrorScreen(retryAction)
            }
        }
    }
}