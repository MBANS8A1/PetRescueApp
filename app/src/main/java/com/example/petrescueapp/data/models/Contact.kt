package com.example.petrescueapp.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("address")
    val address: Address = Address(),
    @SerialName("email")
    val email: String = "",
    @SerialName("phone")
    val phone: String = ""
)