package com.example.petrescueapp.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("embed")
    val embed: String = ""
)