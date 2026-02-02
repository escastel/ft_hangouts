package com.example.ft_hangouts.ui.state

import com.example.ft_hangouts.data.models.Message
import com.example.ft_hangouts.data.models.Contact

data class ChatUiState(
    val contact: Contact? = null,
    val messages: List<Message> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = false
)