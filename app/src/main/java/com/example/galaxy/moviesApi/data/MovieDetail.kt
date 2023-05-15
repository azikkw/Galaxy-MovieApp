package com.example.galaxy.moviesApi.data

data class MovieDetail (
    var id: Int?,
    var title: String?,
    var overview: String?,
    var backdropPath: String?,
    var posterPath: String?,
    var releaseDate: String?,
    var voteAverage: Double?,
    var voteCount: Int?,
    var popularity: Double?,
    val budget: Int?,
    val addedUserId: String?
)