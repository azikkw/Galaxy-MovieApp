package com.example.galaxy.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.galaxy.moviesApi.MovieService
import com.example.galaxy.moviesApi.data.Movie
import com.example.galaxy.moviesApi.data.MovieContainer

class MoviesPagingSource (
    private val movieService: MovieService
): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = movieService.moviesList(
                listId = page,
                apiKey = "f53f2d155af65fc4b2bdb1d89a0081b4",
                language = "en-US"
            )
            val data = response.items.map { movie ->
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

            LoadResult.Page (
                data = data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (data.isEmpty()) null else page.plus(1),
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

}