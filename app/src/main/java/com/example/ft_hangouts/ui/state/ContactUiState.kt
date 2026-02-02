package com.example.ft_hangouts.ui.state

data class ContactUiState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
    val notes: String = "",
    val imageUri: String? = null,
    val isLoading: Boolean = false,
    val firstNameError: String? = null,
    val phoneNumberError: String? = null
)