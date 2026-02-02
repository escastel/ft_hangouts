package com.example.ft_hangouts.data.models

data class Contact(
    val id: Long = 0,
    val firstName: String,
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String,
    val address: String = "",
    val notes: String = "",
    val imageUri: String? = null
) {
    val fullName: String
        get() = if (lastName.isBlank()) firstName else "$firstName $lastName"
}
