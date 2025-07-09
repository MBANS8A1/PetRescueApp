package com.example.petrescueapp.domain.paginator

interface PetPaginator<Page,Content> {
    suspend fun fetchNextPage()
    fun resetPage()
}