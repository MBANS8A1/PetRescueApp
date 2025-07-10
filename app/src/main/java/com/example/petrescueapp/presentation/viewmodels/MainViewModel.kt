package com.example.petrescueapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petrescueapp.Graph
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.domain.repository.PetRepository
import com.example.petrescueapp.utils.ResourceHolder

class MainViewModel(
    private val repository: PetRepository =  Graph.petRepository
):ViewModel() {

    var uiState by mutableStateOf(Uistate())
    companion object{
        const val TAG = "myModel"
    }

}


data class Uistate(
    val animals: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadMoreButton: Boolean = true,
    val startInfiniteScrolling: Boolean = false
)