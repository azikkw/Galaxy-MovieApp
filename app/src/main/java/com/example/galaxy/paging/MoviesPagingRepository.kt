//package com.example.galaxy.paging
//
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import com.example.galaxy.moviesApi.MovieService
//import javax.inject.Inject
//
//class MoviesPagingRepository
//@Inject
//constructor (
//    private val movieService: MovieService
//) {
//    fun getMovies() = Pager (
//        config = PagingConfig(
//            pageSize = 20,
//        ),
//        pagingSourceFactory = {
//            MoviesPagingSource(movieService)
//        }
//    ).flow
//}