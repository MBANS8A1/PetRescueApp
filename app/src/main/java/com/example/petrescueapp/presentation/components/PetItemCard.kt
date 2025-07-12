package com.example.petrescueapp.presentation.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petrescueapp.R
import com.example.petrescueapp.domain.models.Pet

@Composable
fun PetItemCard(
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
           .padding(16.dp),
           horizontalArrangement = Arrangement.SpaceBetween
       ){
           var isLoading:Boolean by remember {
               mutableStateOf(false)
           }
           Row{
               AsyncImage(modifier = Modifier.size(80.dp,80.dp)
                   .clip((RoundedCornerShape(16.dp))),
                   model = if(pet.photos.isNotEmpty()) pet.photos[0].medium
                           else null,
                   placeholder = painterResource(id=R.drawable.placeholder_ic),
                   contentDescription="",
                   contentScale = ContentScale.Crop,
                   alignment = Alignment.CenterStart,
                   onLoading = {
                        isLoading =true
                   },
                   onError = {
                       it.result.throwable.printStackTrace()
                   },
                   onSuccess = {
                       isLoading = false
                   }
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
    PetItemCard(petItem){}
}