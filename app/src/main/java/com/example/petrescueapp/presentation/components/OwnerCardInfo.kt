package com.example.petrescueapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petrescueapp.R
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.presentation.model.Owner


@Composable
fun OwnerCardInfo(pet:Pet) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(5.dp)

    ){
        Text(text=" Pet Contact Information", fontSize = 20.sp, fontWeight = FontWeight.W600)

    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(text= "${pet.contact.phone}",
            color =  MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        // Spacer(modifier = Modifier.width(10.dp))
        Text(text= pet.contact.email,
            color =  MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        //Spacer(modifier = Modifier.width(10.dp))
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clickable{
                }.
                clip(CircleShape),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.size(20.dp)){
                Icon(painter = painterResource(id= R.drawable.ic_messenger),
                    contentDescription = null,
                    tint = Color.White
                )

            }
        }
    }
}




//@Composable
//fun OwnerCardInfo(owner: Owner) {
//    Spacer(modifier = Modifier.height(16.dp))
//    Row(horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(painter= painterResource(owner.image),
//                contentDescription =null,
//                modifier = Modifier
//                    .size(60.dp, 60.dp)
//                    .clip(RoundedCornerShape(16.dp)),
//                alignment = Alignment.CenterStart,
//                contentScale = ContentScale.Crop
//
//                )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column {
//                Text(text= owner.name,
//                    color =  MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.labelMedium,
//                    fontWeight = FontWeight.W600,
//                    textAlign = TextAlign.Start
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text= owner.basicInfo,
//                    color =  MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.labelSmall
//                )
//
//            }
//        }
//        Surface(
//            modifier = Modifier
//                .size(40.dp)
//                .clickable{
//                }.
//            clip(CircleShape),
//            shape = CircleShape,
//            color = MaterialTheme.colorScheme.primary,
//            contentColor = MaterialTheme.colorScheme.onPrimary
//        ) {
//            Box(contentAlignment = Alignment.Center,
//                modifier = Modifier.size(20.dp)){
//                Icon(painter = painterResource(id= R.drawable.ic_messenger),
//                    contentDescription = null,
//                    tint = Color.White
//                )
//
//            }
//        }
//    }
//}


