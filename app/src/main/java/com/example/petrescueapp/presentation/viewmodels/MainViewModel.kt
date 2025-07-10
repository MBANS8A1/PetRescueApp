package com.example.petrescueapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.petrescueapp.Graph
import com.example.petrescueapp.domain.repository.PetRepository

class MainViewModel(
    private val repository: PetRepository =  Graph.petRepository
):ViewModel() {
}