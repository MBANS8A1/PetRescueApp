package com.example.petrescueapp.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    @SerialName("primary")
    val primary: String = "",
    @SerialName("secondary")
    val secondary: Any? = null,
    @SerialName("tertiary")
    val tertiary: Any? = null
)