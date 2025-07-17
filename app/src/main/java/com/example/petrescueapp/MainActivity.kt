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
import androidx.navigation.compose.rememberNavController
import com.example.petrescueapp.presentation.detail.DetailScreen
import com.example.petrescueapp.presentation.home.Home
import com.example.petrescueapp.presentation.navigation.PetRescueNavigation
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
            var id by remember{
                mutableIntStateOf(-1)
            }
            val navController  = rememberNavController()
            PetRescueAppTheme(
                darkTheme = isDarkTheme
            ) {
                Surface(modifier = Modifier.fillMaxSize(),
                    color= MaterialTheme.colorScheme.background
                    ){
                    PetRescueNavigation(
                        navController = navController,
                        uistate = viewModel.uiState,
                        onThemeChange = {isDarkTheme = !isDarkTheme},
                        onLoadNextPage = viewModel::loadNextPetsPage,
                        onInfiniteScrollChange = viewModel::onInfiniteScrollChange
                    )
                }
            }
        }
    }
}

