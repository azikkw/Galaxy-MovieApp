package com.example.galaxy.moviesApi

import com.example.galaxy.moviesApi.data.MovieDetail
import com.example.galaxy.moviesApi.models.Movie
import com.example.galaxy.moviesApi.models.Movies
import dagger.Binds
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface MovieService {

    @GET("list/{list_id}")
    suspend fun moviesList (
        @Path("list_id") listId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Movies

    @GET("movie/{movie_id}")
    suspend fun movieDetail (
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieDetail

}