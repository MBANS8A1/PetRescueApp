package com.example.petrescueapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petrescueapp.Graph
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.paginator.LoadingStateListener
import com.example.petrescueapp.domain.paginator.PetPaginatorImplementation
import com.example.petrescueapp.domain.repository.PetRepository
import com.example.petrescueapp.utils.ResourceHolder

class MainViewModel(
    private val repository: PetRepository =  Graph.petRepository
):ViewModel(),LoadingStateListener<ResourceHolder<List<Pet>>> {

    var uiState by mutableStateOf(Uistate())
    companion object{
        const val TAG = "myModel"
    }
    private val petPaginator: PetPaginatorImplementation(
        initialKey = getPage(uiState.animals.data),
        loadingState =this,
        onRequest = { page ->
            if(uiState.isFetchingPet)
                return@PetPaginatorImplementation ResourceHolder.Loading()

             val pet = fetchAnimals(page)
        },
        getNextPage = {result ->
            getPage(result.data)
        }
    )

    //helper method to get Pet information
    private suspend fun fetchAnimals(page:Int):ResourceHolder<List<Pet>>{
        return repository.getAnimal(page)
    }

    private fun getPage(pageSource: List<Pet>?):Int{
       return if(pageSource?.isEmpty() == true){
            pageSource[pageSource.lastIndex].currentPage + 1
        }else 1
    }

    override fun onLoadingStateChanged(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onError(error: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onDataFetched(data: ResourceHolder<List<Pet>>) {
        TODO("Not yet implemented")
    }

}
data class Uistate(
    val animals: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadMoreButton: Boolean = true,
    val startInfiniteScrolling: Boolean = false
)