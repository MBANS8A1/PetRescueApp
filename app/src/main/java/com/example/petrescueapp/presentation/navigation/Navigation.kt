package com.example.petrescueapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petrescueapp.presentation.home.Home

enum class Screen{
    Home,
    Detail
}

@Composable
fun PetRescueNavigation(
    navController: NavHostController,
    uistate: UiState
) {
    NavHost(navController = navController,
           startDestination = Screen.Home.name,

        ){
            composable(route=Screen.Home.name){
                Home(
                    uistate = uistate,
                    onSwitchClick = { isDarkTheme = !isDarkTheme
                    },

                    onPetClick ={ selectedId->

                    } ,
                    onLoadNextPage = viewModel::loadNextPetsPage,
                    onInfiniteScrollingChange = {
                        viewModel.onInfiniteScrollChange(it)
                    }


                )
            }
    }
}

