package com.example.ft_hangouts.ui.state

import com.example.ft_hangouts.data.models.Conversation

data class ConversationListUiState(
    val conversations: List<Conversation> = emptyList(),
    val isLoading: Boolean = false
)