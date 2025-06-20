package com.example.petrescueapp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.petrescueapp.data.DummyPetDataSource
import com.example.petrescueapp.data.model.Pet

@Composable
fun PetInfoItem(
    pet: Pet,
    onPetItemClick: (Pet) -> Unit
) {
   Card(modifier=Modifier
       .fillMaxWidth()
       .padding(8.dp)
       .clip(RoundedCornerShape(16.dp))
       .clickable(onClick = {onPetItemClick(pet)}),
        elevation =CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
   ) {
       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(start = 16.dp,top=40.dp, end =16.dp, bottom =16.dp),
           horizontalArrangement = Arrangement.SpaceBetween
       ){
           Row{
               Image(modifier = Modifier.size(80.dp,80.dp)
                   .clip((RoundedCornerShape(16.dp)))
                   ,
                   painter= painterResource(id=pet.image),
                   contentDescription="",
                   contentScale = ContentScale.Crop,
                   alignment = Alignment.CenterStart
               )
               Spacer(modifier=Modifier.width(16.dp))

               Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                   Text(text=pet.name,
                       color = MaterialTheme.colorScheme.onSurface,
                       fontWeight = FontWeight.Bold,
                       style = MaterialTheme.typography.bodyMedium
                   )
                   Spacer(modifier=Modifier.height(8.dp))
                   Text(
                       text = buildString {
                           append(pet.age)
                           append(" | ")
                           append(pet.breed)
                       },
                       color=MaterialTheme.colorScheme.onSurface,
                       style = MaterialTheme.typography.bodySmall

                   )
                   Spacer(modifier=Modifier.height(8.dp))
                   Row(
                       verticalAlignment = Alignment.Bottom
                   ) {
                       Icon(painter = painterResource(id=R.drawable.ic_location),
                           contentDescription = null,
                           modifier=Modifier.size(16.dp,16.dp),
                           tint = androidx.compose.ui.graphics.Color.Red
                       )
                       Text(text=pet.location, modifier = Modifier.padding(
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
               GenderTag(gender=pet.gender, modifier=Modifier)
               Text(
                   text = "Adoptable",
                   style=MaterialTheme.typography.bodySmall,
                   color =  MaterialTheme.colorScheme.onSurface
               )
           }
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
    //using dummy data source
    //get a random pet icon
    val petItem = DummyPetDataSource.dogList.random()
    PetInfoItem(petItem){}
}