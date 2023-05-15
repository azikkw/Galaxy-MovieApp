package com.example.galaxy.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.galaxy.R
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.navigation.BottomNavItem
import com.example.galaxy.views.MovieDetailUiState
import com.example.galaxy.views.MoviesPagingViewModel
import com.example.galaxy.views.MoviesUiState
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesScreen (
    moviesUiState: MoviesUiState,
    moviesDetailUiState: MovieDetailUiState,
    movieDetail: MovieDetail,
    retryAction: () -> Unit,
    navController: NavController,
    moviesPagingViewModel: MoviesPagingViewModel
) {
//    val moviesPagingViewModel = hiltViewModel<MoviesPagingViewModel>()
    val movies = moviesPagingViewModel.movies.collectAsLazyPagingItems()
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

            Text (
                text = "All movies list",
                color = Color(255, 255, 255, 250),
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.quicksandbold)),
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
            )

            LazyColumn {
                items(movies) { movie ->
//                    if(movie != null) {
//                        MovieCard (
//                            movie = movie,
//                            gradient = Brush.linearGradient (
//                                colors = listOf(Color(92, 76, 200, 255), Color(29, 116, 216)),
//                                tileMode = TileMode.Repeated
//                            ),
//                            paddingStart = 8.dp,
//                            paddingEnd = 10.dp,
//                            state = true,
//                            navController = navController,
//                            movieDetail = movieDetail,
//                            moviesDetailUiState = moviesDetailUiState
//                        )
//                    }
//                    if(movie != null) {
//                        movie.posterPath?.let {
//                            AsyncImage(
//                                model = "https://image.tmdb.org/t/p/w500/$it",
//                                contentDescription = "Poster path",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .padding(bottom = 8.dp)
//                                    .clip(RoundedCornerShape(8.dp))
//                                    .width(100.dp)
//                                    .height(200.dp)
//                            )
//                        }
//                    }
                    if(movie != null) {
                        movie.title?.let { Text(text = it) }
                    }
                }
            }
        }
    }
}