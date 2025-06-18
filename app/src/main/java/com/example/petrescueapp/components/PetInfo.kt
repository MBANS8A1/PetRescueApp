package com.example.petrescueapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
            Image(modifier = Modifier.size(80.dp,80.dp)
                .clip((RoundedCornerShape(16.dp)))
                ,
                painter= painterResource(R.drawable.blue_dog),
                contentDescription="",
                contentScale = ContentScale.Crop)
            Spacer(modifier=Modifier.width(16.dp))

            Column {
                Text("Pogo",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier=Modifier.height(8.dp))
                Text(
                    text = buildString {
                        append("Adult")
                        append("|")
                        append("Domestic Short Hair")
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevPetInfo() {
    PetInfoItem()
}