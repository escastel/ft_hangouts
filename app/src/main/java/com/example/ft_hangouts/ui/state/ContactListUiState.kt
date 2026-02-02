package com.example.ft_hangouts.ui.state

import com.example.ft_hangouts.data.models.Contact

data class ContactListUiState(
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = false
)