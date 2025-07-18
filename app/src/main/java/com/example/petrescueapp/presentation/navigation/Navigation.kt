package com.example.petrescueapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petrescueapp.presentation.detail.DetailScreen
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
                        navController.navigate("${Screen.Detail.name}/$selectedId")
                    } ,
                    onLoadNextPage = onLoadNextPage,
                    onInfiniteScrollingChange = onInfiniteScrollChange
                )
            }
            composable(route="${Screen.Detail.name}/{id}"){
                DetailScreen(
                    pet = uistate.animals.data?.get(0)!!
                ) {
                    navController.navigate(Screen.Home.name)
                }
            }
    }
}



