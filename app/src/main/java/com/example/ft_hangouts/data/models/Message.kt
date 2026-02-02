package com.example.ft_hangouts.data.models

data class Message(
    val id: Long = 0,
    val contactId: Long,
    val content: String,
    val timestamp: Long,
    val isReceived: Boolean
)