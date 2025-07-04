package com.example.petrescueapp.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    @SerialName("mixed")
    val mixed: Boolean = false,
    @SerialName("primary")
    val primary: String = "",
    @SerialName("secondary")
    val secondary: Any? = null,
    @SerialName("unknown")
    val unknown: Boolean = false
)