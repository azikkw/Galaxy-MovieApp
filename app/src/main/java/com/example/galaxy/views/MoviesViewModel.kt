package com.example.galaxy.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.galaxy.GalaxyApplication
import com.example.galaxy.moviesApi.MovieService
import com.example.galaxy.moviesApi.MoviesRepository
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.paging.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MoviesUiState {
    data class Success(val moviesList: List<Movie>): MoviesUiState
    object Error: MoviesUiState
    object Loading: MoviesUiState
}

sealed interface MovieDetailUiState {
    data class Success(val movieDetail: MovieDetail): MovieDetailUiState
    object Error: MovieDetailUiState
    object Loading: MovieDetailUiState
}

class MoviesViewModel (
    private val moviesRepository: MoviesRepository
): ViewModel() {

    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    var movieDetailUiState: MovieDetailUiState by mutableStateOf(MovieDetailUiState.Loading)
        private set

    init {
        getMoviesList()
    }

    fun getMoviesList (
        listId: Int = 10,
        apiKey: String = "f53f2d155af65fc4b2bdb1d89a0081b4",
        language: String = "en-US"
    ) {
        viewModelScope.launch {
            moviesUiState = MoviesUiState.Loading
            moviesUiState = try {
                MoviesUiState.Success(moviesRepository.getMovies(listId, apiKey, language))
            } catch(e: IOException) {
                MoviesUiState.Error
            } catch (e: HttpException) {
                MoviesUiState.Error
            }
        }
    }

    fun getMovieDetail (
        movieId: Int,
        apiKey: String = "f53f2d155af65fc4b2bdb1d89a0081b4",
        language: String = "en-US"
    ) {
        viewModelScope.launch {
            movieDetailUiState = MovieDetailUiState.Loading
            movieDetailUiState = try {
                MovieDetailUiState.Success(moviesRepository.getMovie(movieId, apiKey, language))
            } catch(e: IOException) {
                MovieDetailUiState.Error
            } catch(e: HttpException) {
                MovieDetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GalaxyApplication)
                val moviesRepository = application.container.moviesRepository
                MoviesViewModel(moviesRepository = moviesRepository)
            }
        }
    }
}