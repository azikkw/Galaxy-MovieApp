package com.example.galaxy.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.galaxy.GalaxyApplication
import com.example.galaxy.moviesApi.MovieService
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.paging.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesPagingViewModel(movieService: MovieService): ViewModel() {
    val movies: Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 6)
    ) {
        MoviesPagingSource(movieService)
    }.flow.cachedIn(viewModelScope)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GalaxyApplication)
                val movieService = application.container.retrofitService
                MoviesPagingViewModel(movieService = movieService)
            }
        }
    }
}