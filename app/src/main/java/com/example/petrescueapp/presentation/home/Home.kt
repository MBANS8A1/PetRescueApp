package com.example.petrescueapp.presentation.home


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.petrescueapp.presentation.components.PetItemCard
import com.example.petrescueapp.presentation.components.TopBar
import com.example.petrescueapp.presentation.data.DummyPetDataSource

@Composable
fun Home(
    onSwitchClick:() ->Unit,
    onPetClick:(Int)-> Unit    //index of the Pet
) {
    val petList = DummyPetDataSource.dogList
    Scaffold(
        topBar = {
            TopBar{
                onSwitchClick()
            }
        }
    ) {
        paddingValues: PaddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(petList){index,pet->
                PetItemCard(pet) {
                    onPetClick(index)

                }
            }
        }

    }
}

@Preview
@Composable
private fun PrevItem() {
    Home(onSwitchClick = {},){}
}