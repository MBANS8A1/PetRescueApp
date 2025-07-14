package com.example.petrescueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petrescueapp.presentation.detail.DetailScreen
import com.example.petrescueapp.presentation.home.Home
import com.example.petrescueapp.presentation.ui.theme.PetRescueAppTheme
import com.example.petrescueapp.presentation.viewmodels.MainViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = viewModel(modelClass= MainViewModel::class.java)
            var isDarkTheme by remember{
                mutableStateOf(false)
            }
            var currentScreen by remember{
                mutableStateOf(Screen.Home)
            }
            var id by remember{
                mutableIntStateOf(-1)
            }
            PetRescueAppTheme(
                darkTheme = isDarkTheme
            ) {
                Surface(modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colorScheme.background
                    ){
                    when(currentScreen){
                        Screen.Home ->{
                            Home(
                                uistate = viewModel.uiState,
                                onSwitchClick = { isDarkTheme = !isDarkTheme
                            },

                                onPetClick ={ selectedId->
                                    currentScreen = Screen.Detail
                                    id = selectedId
                                } ,
                                onLoadNextPage = viewModel::loadNextPetsPage,
                                onInfiniteScrollingChange = {
                                    viewModel.onInfiniteScrollChange(it)
                                }


                            )
                        }
                        Screen.Detail->{
                            DetailScreen(
                                pet = viewModel.uiState.animals.data?.get(id)!!
                            ) {
                                currentScreen = Screen.Home
                            }
                        }

                    }
                }
            }
        }
    }
}

enum class Screen{
    Home,
    Detail
}