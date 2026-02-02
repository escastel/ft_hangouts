package com.example.ft_hangouts.ui.state

data class ContactUiState(
    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
    val notes: String = "",
    val imageUri: String? = null,
    val isLoading: Boolean = false
)