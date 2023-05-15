package com.example.galaxy.views

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galaxy.moviesApi.data.Movie
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class FavoritesViewModel
@Inject
constructor(): ViewModel() {

    var favoriteMovies by mutableStateOf<MutableList<Movie>>(mutableListOf())

    init {
        viewModelScope.launch {
            favoriteMovies = getFavoriteMovies().toMutableList()
        }
    }

    private suspend fun getFavoriteMovies(): List<Movie> {
        return Firebase.firestore.collection("favorites").get().await().map { movie ->
            movie.toObject(Movie::class.java)
        }
    }

}