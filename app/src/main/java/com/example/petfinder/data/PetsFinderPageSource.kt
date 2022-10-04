package com.example.petfinder.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.petfinder.domain.response.Animal
import com.example.petfinder.domain.response.Api
import com.example.petfinder.domain.response.ListPetApi
import com.example.petfinder.domain.response.PetResponse
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

class PetsFinderPageSource  constructor(
    private val listPetApi: ListPetApi
) : PagingSource<Int, Animal>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Animal> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = listPetApi.getListPets(
                page = pageIndex
            )
            val pets = response.animals
            val nextKey =
                if (pets.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / Api.MAX_PAGE_SIZE)
                }
            LoadResult.Page(
                data = pets,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Animal>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val TMDB_STARTING_PAGE_INDEX = 1
    }
}