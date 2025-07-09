package com.example.petrescueapp.domain.paginator

class PetPaginatorImplementation<Page,Result>(
    private val initialKey: Page,
    private val loadingState
):PetPaginator<Page,Result>

 {
    override suspend fun fetchNextPage() {
        TODO("Not yet implemented")
    }

    override fun resetPage() {
        TODO("Not yet implemented")
    }
}