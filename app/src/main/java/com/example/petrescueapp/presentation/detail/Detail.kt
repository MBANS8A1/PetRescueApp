package com.example.petrescueapp.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petrescueapp.R
import com.example.petrescueapp.domain.models.Pet
import com.example.petrescueapp.presentation.components.InfoCard
import com.example.petrescueapp.presentation.components.OwnerCardInfo
import com.example.petrescueapp.presentation.components.PetInfoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(pet: Pet, onNavigate:()->Unit) {
    Scaffold(
        topBar = {
            //non-custom topBar so using TopAppBar
            TopAppBar(
                title = {
                    Text(text="Detail")
                },
                navigationIcon = {
                    Spacer(modifier = Modifier.width(50.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                onNavigate.invoke()
                            },
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults
                    .topAppBarColors(containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface
                    )
            )
        }
    ) {padding ->
        //use dummy data source for now
        LazyColumn(contentPadding = padding) {
            item {
                if(isLoading){
                    CircularProgressIndicator()
                }
                AsyncImage(modifier = Modifier.size(80.dp,80.dp)
                    .clip((RoundedCornerShape(16.dp))),
                    model = if(pet.photos.isNotEmpty()) pet.photos[0].medium
                    else null,
                    placeholder = painterResource(id= R.drawable.placeholder_ic),
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
                Spacer(modifier = Modifier.height(16.dp))
                PetInfoItem(name=pet.name,gender=pet.gender, location = pet.location)
            } //image and basic pet info
            item{
                MyStoryItem(pet = pet)

            } //pet story and title
            item{ //pet information via info cards
                PetInfo(pet = pet)
            }// pet information on cards
            item{ //owner information
                OwnerCardInfo(owner = pet.owner)
            }
            item{
                PetButton {

                }
            }
        }
    }
}

@Composable
fun PetButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(36.dp))
    Button(onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)) {
        Text(text= "Adopt Me")
    }
    Spacer(modifier = Modifier.height(24.dp))

}

@Composable
fun PetInfo(pet: Pet){
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            InfoCard(primaryText =pet.age ,
                secondaryText ="Age",
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(4.dp)
            )
            InfoCard(primaryText =pet.color,
                secondaryText ="Colour",
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(4.dp)
                )
            InfoCard(primaryText =pet.breed,
                secondaryText ="Breed",
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(4.dp)
                )

        }
    }
}




@Composable
fun MyStoryItem(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title="My Story")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text= pet.description, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style=  MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start)
    }
}

@Composable
fun Title(title:String) {
    Text(
        text=title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.dp)
        ,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}




