package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.models.Conversation
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import com.example.ft_hangouts.utils.DateUtils

@Composable
fun ConversationItem(
    chat: Conversation,
    onClick: () -> Unit
) {
    val isUnknown = chat.contactName.startsWith("+") ||
            chat.contactName.firstOrNull()?.isDigit() == true
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            if (!chat.contactImageUri.isNullOrBlank()) {
                ContactAvatar(
                    name = chat.contactName,
                    imageUri = chat.contactImageUri,
                    size = 150.dp,
                    isUnknown = chat.contactName.firstOrNull()?.isDigit() == true || chat.contactName.startsWith("+")
                )
            } else if (isUnknown) {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(10.dp)
                )
            } else {
                Text(
                    text = chat.contactName.take(1).uppercase(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.contactName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = DateUtils.formatTime(chat.timestamp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ConversationItemPreview(){
    val timestamp = System.currentTimeMillis()
    Ft_hangoutsTheme {
        ConversationItem(
            chat = Conversation(
                contactId = 0,
                contactName = "Name",
                lastMessage = "Hello",
                timestamp = timestamp,
                contactImageUri = null
            ),
            onClick = {}
        )
    }
}