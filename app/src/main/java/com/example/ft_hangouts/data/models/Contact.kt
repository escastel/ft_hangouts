package com.example.ft_hangouts.data.models

data class Contact(
    val id: Long = 0,
    val name: String,
    val phonePrefix: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)