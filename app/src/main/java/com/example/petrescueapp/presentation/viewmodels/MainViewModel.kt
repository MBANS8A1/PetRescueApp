package com.example.petrescueapp.presentation.viewmodels

import android.util.Log
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petrescueapp.Graph
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.paginator.LoadingStateListener
import com.example.petrescueapp.domain.paginator.PetPaginatorImplementation
import com.example.petrescueapp.domain.repository.PetRepository
import com.example.petrescueapp.utils.ResourceHolder
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PetRepository =  Graph.petRepository
):ViewModel(),LoadingStateListener<ResourceHolder<List<Pet>>> {

    var uiState by mutableStateOf(Uistate())
    companion object{
        const val TAG = "myModel"
    }
    private val petPaginator = PetPaginatorImplementation(
        initialKey = getPage(uiState.animals.data),
        loadingState =this,
        onRequest = { page ->
            if(uiState.isFetchingPet)
                return@PetPaginatorImplementation ResourceHolder.Loading()
             val pet = fetchAnimals(page)
             pet
        },
        getNextPage = {result ->
            getPage(result.data)
        }
    )

    init{
        loadNextPetsPage()
    }

    //Removed the private access modifier
    fun loadNextPetsPage(){
        viewModelScope.launch {
            petPaginator.fetchNextPage()
        }
    }


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
        uiState.copy(isFetchingPet = isLoading)
    }

    override fun onError(error: Throwable) {
        Log.e(TAG,"onError: Fetching Pet error",error)
    }

    override fun onDataFetched(data: ResourceHolder<List<Pet>>) {
        uiState =uiState.updateAnimal(data)
    }

    private fun Uistate.updateAnimal(newData:ResourceHolder<List<Pet>>):Uistate{
        return when(newData){
            is ResourceHolder.Success ->{
                val updatedData = this.animals.data?.combineData(newData.data!!) ?: newData
                copy(animals = updatedData)
            }
            is ResourceHolder.Error ->{
                copy(animals = newData)
            }
            else ->this //return the same data
        }
    }

    private fun <Data> List<Data>.combineData(newList:List<Data>):ResourceHolder<List<Data>>{
        return ResourceHolder.Success(data=this.union(newList).toList())
    }

}
data class Uistate(
    val animals: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadMoreButton: Boolean = true,
    val startInfiniteScrolling: Boolean = false
)