package com.example.ft_hangouts.ui.state

import androidx.annotation.StringRes

data class ContactUiState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val address: String = "",
    val notes: String = "",
    val imageUri: String? = null,
    val isLoading: Boolean = false,
    @StringRes val firstNameError: Int? = null,
    @StringRes val phoneNumberError: Int? = null,
    @StringRes val emailError: Int? = null
)