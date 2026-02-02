package com.example.ft_hangouts.ui.state

import com.example.ft_hangouts.data.models.Contact

data class ContactDetailUiState(
    val contact: Contact? = null,
    val isLoading: Boolean = false
)