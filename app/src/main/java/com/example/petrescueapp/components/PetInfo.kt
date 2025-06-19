package com.example.petrescueapp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.example.petrescueapp.R

@Composable
fun PetInfoItem() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier=Modifier.height(8.dp))
                Text(
                    text = buildString {
                        append("Adult")
                        append(" | ")
                        append("Domestic Short Hair")
                    },
                    color=MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall

                )
                Spacer(modifier=Modifier.height(8.dp))
                Row {
                    Icon(painter = painterResource(id=R.drawable.ic_location),
                        contentDescription = null,
                        modifier=Modifier.size(16.dp,16.dp),
                        tint = androidx.compose.ui.graphics.Color.Red
                        )
                    Text(text="Toronto US", modifier = Modifier.padding(
                        start=8.dp,
                        top=0.dp,
                        end=12.dp,
                        bottom=0.dp
                    )
                        ,color = MaterialTheme.colorScheme.onSurface,
                        style= MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Column(
            modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GenderTag(gender="Male", modifier=Modifier)
            Text(
                text = "Adoptable",
                style=MaterialTheme.typography.bodySmall,
                color =  MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun GenderTag(gender:String,modifier:Modifier){
    val colour = if(gender == "Male"){
        Color.Blue
    }else{
        Color.Red
    }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colour.copy(alpha=0.2f))


    ){
        Text(
            text=gender,
            modifier = Modifier.padding(12.dp,4.dp,12.dp,6.dp),
            style = MaterialTheme.typography.bodySmall,
            color= colour

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevPetInfo() {
    PetInfoItem()
}