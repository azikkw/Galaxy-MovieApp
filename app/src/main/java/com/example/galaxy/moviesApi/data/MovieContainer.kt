package com.example.galaxy.moviesApi.data

import com.example.galaxy.moviesApi.MovieService
import com.example.galaxy.moviesApi.MoviesRepository
import com.example.galaxy.moviesApi.NetworkMoviesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MovieContainer {
    val moviesRepository: MoviesRepository
    val retrofitService: MovieService
}

class DefaultMovieContainer: MovieContainer {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    override val retrofitService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

    override val moviesRepository: MoviesRepository by lazy {
        NetworkMoviesRepository(retrofitService)
    }

}
