package com.example.petrescueapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petrescueapp.R

@Composable
fun PetInfoItem() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ){
        Row{
            Image(modifier = Modifier.size(80.dp,80.dp),
                painter= painterResource(R.drawable.blue_dog),
                contentDescription="")
        }
    }
}

@Preview
@Composable
private fun PrevPetInfo() {
    PetInfoItem()
}