package com.example.petrescueapp.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            composable(route="${Screen.Detail.name}/{id}",
                    arguments = listOf(navArgument("id"){type = NavType.IntType})
                ){ backStackEntry ->
                    val id = backStackEntry.arguments!!.getInt("id")
                    val selectedPet = uistate.animals.data?.get(id)!!
                    val context =  LocalContext.current
                    DetailScreen(
                        pet = selectedPet,
                        onPetBtnClick = {
                            openUrl(
                                context = context,
                                url = selectedPet.url

                            )
                        }
                ) {
                   navController.navigateUp()

                }
            }
    }
}

private fun openUrl(
    context:Context,
    url:String
){
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    }catch (e:Exception){
        e.printStackTrace()
    }
}



