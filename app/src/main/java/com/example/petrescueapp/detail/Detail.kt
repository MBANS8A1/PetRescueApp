package com.example.petrescueapp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petrescueapp.components.OwnerCardInfo
import com.example.petrescueapp.components.PetBasicInfo
import com.example.petrescueapp.data.DummyPetDataSource
import com.example.petrescueapp.data.model.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(index:Int,onNavigate:()->Unit) {
    Scaffold(
        topBar = {
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
        val pet = DummyPetDataSource.dogList[index]
        LazyColumn(contentPadding = padding) {
            item {
                Image(painter = painterResource(id=pet.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.height(16.dp))
                PetBasicInfo(name=pet.name,gender=pet.gender, location = pet.location)
            } //image and basic pet info
            item{
                MyStoryItem(pet = pet)

            } //pet story and title
            item{ //pet information via info cards
                PetInfo(pet = pet)
            }
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
fun PetInfo(pet:Pet){
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
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String
) {
    Surface(shape=MaterialTheme.shapes.medium,
        modifier = Modifier) {
        Column(modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            //Material 3 way of making opacity changes below
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onSurface
                    .copy(alpha = 0.4f)
            ) {
                Text(text = secondaryText)
            }

            Text(text=primaryText,
                style=MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(4.dp))
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

@Preview(showSystemUi = true)
@Composable
private fun PrevDetailScreen() {
    DetailScreen(index=0) {  }
}

@Preview(showBackground = true)
@Composable
private fun PrevInfoCard() {
    val pet = DummyPetDataSource.dogList[0]
    PetInfo(pet=pet)
}


