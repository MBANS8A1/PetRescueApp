package com.example.petrescueapp.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Self(
    @SerialName("href")
    val href: String = ""
)