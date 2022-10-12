package com.example.petfinder.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.petfinder.domain.repository.PetPagedListRepository
import com.example.petfinder.presentation.screen.model.PetModel
import javax.inject.Inject

class PetsFinderPageSource @Inject constructor(
    private val petPagedListRepository: PetPagedListRepository,
) : PagingSource<Int, PetModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetModel> {
        val page = params.key ?: 1
        return runCatching {
            petPagedListRepository.getListPets(page)
        }.fold(
            onSuccess = { animal ->
                LoadResult.Page(
                    data = animal,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (animal.isEmpty()) null else page + 1
                )
            },
            onFailure = { error ->
                LoadResult.Error(error)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PetModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(STARTING_PAGE) ?: anchorPage.nextKey?.minus(STARTING_PAGE)
    }

    companion object {
        private const val STARTING_PAGE = 1

    }
}