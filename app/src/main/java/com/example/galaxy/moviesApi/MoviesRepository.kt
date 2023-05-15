package com.example.galaxy.moviesApi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.moviesApi.models.Items
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

interface MoviesRepository {
    suspend fun getMovies(listId: Int, apiKey: String, language: String): List<Movie>
    suspend fun getMovie(movieId: Int, apiKey: String, language: String): MovieDetail
}

class NetworkMoviesRepository (
    private val movieService: MovieService,
): MoviesRepository {

    override suspend fun getMovies (
        listId: Int,
        apiKey: String,
        language: String
    ): List<Movie> = movieService.moviesList(listId, apiKey, language).items.map { movie ->
        Movie (
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            backdropPath = movie.backdropPath,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            popularity = movie.popularity,
            genreIds = movie.genreIds,
            addedUserId = ""
        )
    }

    override suspend fun getMovie (
        movieId: Int,
        apiKey: String,
        language: String
    ): MovieDetail = MovieDetail (
        id = movieService.movieDetail(movieId, apiKey, language).id,
        title = movieService.movieDetail(movieId, apiKey, language).title,
        overview = movieService.movieDetail(movieId, apiKey, language).overview,
        backdropPath = movieService.movieDetail(movieId, apiKey, language).backdropPath,
        posterPath = movieService.movieDetail(movieId, apiKey, language).posterPath,
        releaseDate = movieService.movieDetail(movieId, apiKey, language).releaseDate,
        voteAverage = movieService.movieDetail(movieId, apiKey, language).voteAverage,
        voteCount = movieService.movieDetail(movieId, apiKey, language).voteCount,
        popularity = movieService.movieDetail(movieId, apiKey, language).popularity,
        budget = movieService.movieDetail(movieId, apiKey, language).budget,
        addedUserId = ""
    )

}