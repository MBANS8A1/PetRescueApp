package com.example.petrescueapp.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petrescueapp.components.PetInfoItem
import com.example.petrescueapp.data.DummyPetDataSource

@Composable
fun Home() {
    val petList = DummyPetDataSource.dogList
    LazyColumn {
        items(petList){pet->
            PetInfoItem(pet) {

            }
        }
    }

}

@Preview
@Composable
private fun PrevItem() {
    Home()
}