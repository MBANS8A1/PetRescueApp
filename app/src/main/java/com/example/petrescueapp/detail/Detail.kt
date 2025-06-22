package com.example.petrescueapp.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(onNavigate:()->Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text="Detail")
                },
                navigationIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp,24.dp)
                        .clickable{
                            onNavigate.invoke()
                        }
                    )

                }
            )
        }
    ) {

    }
}