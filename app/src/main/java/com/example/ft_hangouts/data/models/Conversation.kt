package com.example.ft_hangouts.data.models

data class Conversation(
    val contactId: Long,
    val contactName: String,
    val lastMessage: String,
    val timestamp: Long,
    val contactImageUri: String? = null
)