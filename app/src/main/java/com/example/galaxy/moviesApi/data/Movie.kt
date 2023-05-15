package com.example.galaxy.moviesApi.data

data class Movie (
    val id: Int?,
    val title: String?,
    val overview: String?,
    val backdropPath: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    var voteCount: Int?,
    var popularity: Double?,
    val genreIds: ArrayList<Int>,
    val addedUserId: String?
) {
    constructor(): this(0, "", "", "", "", "", 0.0, 0, 0.0, arrayListOf(), "")
}