package com.example.petrescueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petrescueapp.detail.DetailScreen
import com.example.petrescueapp.home.Home
import com.example.petrescueapp.ui.theme.PetRescueAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember{
                mutableStateOf(false)
            }
            var currentScreen by remember{
                mutableStateOf(Screen.Home)
            }
            var selectedIndex by remember{
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
                            Home(onSwitchClick = { isDarkTheme = !isDarkTheme
                            },
                                onPetClick ={ index->
                                    currentScreen = Screen.Detail
                                    selectedIndex = index
                                } )
                        }
                        Screen.Detail->{
                            DetailScreen(index = selectedIndex) {
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