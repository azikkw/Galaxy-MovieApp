package com.example.galaxy

import android.app.Application
import com.example.galaxy.moviesApi.data.DefaultMovieContainer
import com.example.galaxy.moviesApi.data.MovieContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GalaxyApplication: Application() {
    lateinit var container: MovieContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultMovieContainer()
    }
}