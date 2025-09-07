package com.example.petrescueapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petrescueapp.R

@Composable
fun TopBar(onSwitchToggle: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text="Hello Adopter,",textAlign=TextAlign.Start,
                style= MaterialTheme.typography.headlineMedium,
                color=MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(top=30.dp)
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text="Find a new pet near you to adopt!",textAlign=TextAlign.Start,
                style= MaterialTheme.typography.bodySmall,
                color=MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.width(50.dp))
        Row(modifier = Modifier
            .fillMaxWidth().padding(top=45.dp,
                end= 35.dp),
            horizontalArrangement = Arrangement.End
        ) {

            PetSwitch {
                onSwitchToggle.invoke()
            }

        }

    }
}

@Composable
fun PetSwitch(onSwitchToggle:() -> Unit) {
    val icon = if(isSystemInDarkTheme()){
        painterResource(R.drawable.ic_switch_on)
    }
    else{
        painterResource(R.drawable.ic_switch_off)
    }

    Icon(painter= icon, contentDescription = null,
        modifier=Modifier.size(30.dp,30.dp)
        .clickable(
            onClick = {onSwitchToggle.invoke()}
        ).padding(top=6.dp)
        ,
        tint = MaterialTheme.colorScheme.onSurface)
}

@Preview
@Composable
private fun PrevTopBar() {
    TopBar(){}
    
}