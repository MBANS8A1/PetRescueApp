package com.example.petrescueapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petrescueapp.presentation.home.Home
import com.example.petrescueapp.presentation.viewmodels.MainViewModel
import com.example.petrescueapp.presentation.viewmodels.Uistate

enum class Screen{
    Home,
    Detail
}

@Composable
fun PetRescueNavigation(
    navController: NavHostController,
    uistate: Uistate,
    onThemeChange: () -> Unit,
    onLoadNextPage: () -> Unit,
    onInfiniteScrollChange: (Boolean) -> Unit
) {
    NavHost(navController = navController,
           startDestination = Screen.Home.name,

        ){
            composable(route=Screen.Home.name){
                Home(
                    uistate = uistate,
                    onSwitchClick = onThemeChange,

                    onPetClick ={ selectedId->

                    } ,
                    onLoadNextPage = onLoadNextPage,
                    onInfiniteScrollingChange = onInfiniteScrollChange
                )
            }
    }
}

