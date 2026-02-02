package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.data.models.Message
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import com.example.ft_hangouts.utils.DateUtils

@Composable
fun MessageBubble(message: Message) {
    val isMyMessage = !message.isReceived
    val alignment = if (isMyMessage) Alignment.End else Alignment.Start

    val bubbleColor = if (isMyMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (isMyMessage) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

    val shape = if (isMyMessage) {
        RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
    } else {
        RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(bubbleColor, shape)
                .padding(12.dp)
        ) {
            Text(text = message.content, color = textColor)
        }

        Text(
            text = DateUtils.formatTime(message.timestamp),
            fontSize = 10.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun MessageBubblePreview(){
    val timestamp = System.currentTimeMillis()
    Ft_hangoutsTheme {
        MessageBubble(
            message = Message(
                id = 0,
                contactId = 0,
                content = "Hello",
                timestamp = timestamp,
                isReceived = true
            )
        )
    }
}