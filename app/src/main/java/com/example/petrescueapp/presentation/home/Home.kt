package com.example.petrescueapp.presentation.home


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petrescueapp.presentation.components.PetItemCard
import com.example.petrescueapp.presentation.components.TopBar
import com.example.petrescueapp.presentation.data.DummyPetDataSource
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


    val petList = DummyPetDataSource.dogList
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
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align =Alignment.Center)
                    )
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
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrevItem() {
    Home(onSwitchClick = {},){}
}