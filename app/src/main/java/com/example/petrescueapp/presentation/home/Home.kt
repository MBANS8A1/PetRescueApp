package com.example.petrescueapp.presentation.home


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petrescueapp.presentation.components.PetItemCard
import com.example.petrescueapp.presentation.components.TopBar
import com.example.petrescueapp.presentation.viewmodels.Uistate
import com.example.petrescueapp.utils.ResourceHolder

@Composable
fun Home(
    onSwitchClick:() ->Unit,
    onPetClick:(Int)-> Unit,    //index of the Pet
    onLoadNextPage: () -> Unit,
    onInfiniteScrollingChange:(Boolean) -> Unit,
    uistate: Uistate
) {
    var scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar{
                onSwitchClick()
            }
        }
    ) {
        paddingValues: PaddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            when (uistate.animals) {
                is ResourceHolder.Loading ->{
                   item {
                       CircularProgressIndicator(
                           modifier = Modifier
                               .fillMaxSize()
                               .wrapContentSize(align = Alignment.Center)
                       )
                   }
                }
                is ResourceHolder.Success ->{
                    val petList = uistate.animals.data ?: emptyList()
                    itemsIndexed(petList){index,item ->
                        PetItemCard(
                            pet = item,
                            onPetItemClick = {
                                onPetClick.invoke(index)
                            }
                        )
                        LaunchedEffect(key1 = scrollState) {
                            if(index >= petList.lastIndex &&
                                !uistate.isFetchingPet &&
                                uistate.startInfiniteScrolling

                                ){
                                    onLoadNextPage()
                            }
                        }
                    }
                    if(uistate.isFetchingPet){
                        item{
                            CircularProgressIndicator()
                        }
                    }
                    item{
                        AnimatedVisibility(
                            visible = uistate.loadMoreButtonVisible
                        ){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ){
                                TextButton(
                                    onClick = {
                                        onLoadNextPage.invoke()
                                        onInfiniteScrollingChange(true)
                                    }
                                ){
                                    Text(
                                        text="Load More Pets",
                                        color=MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {
                    uistate.animals.throwable?.printStackTrace()
                    item{
                        Text("Error Occurred")
                    }
                }
            }
        }
    }
}

