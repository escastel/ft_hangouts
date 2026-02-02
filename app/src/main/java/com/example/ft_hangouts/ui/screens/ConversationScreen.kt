package com.example.ft_hangouts.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.navigation.NavDestination
import com.example.ft_hangouts.ui.components.ConversationItem
import com.example.ft_hangouts.viewmodel.ChatViewModel

@Composable
fun ConversationScreen(
    navController: NavController,
    viewModel: ChatViewModel = viewModel()
) {
    val uiState = viewModel.conversationListUiState

    LaunchedEffect(Unit) {
        viewModel.loadConversations()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (uiState.conversations.isEmpty()) {
            Text(
                text = stringResource(R.string.no_messages),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Gray
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.conversations) { chat ->
                    ConversationItem(chat = chat) {
                        navController.navigate(NavDestination.Chat.createRoute(chat.contactId))
                    }
                    Divider(color = Color.LightGray.copy(alpha = 0.5f))
                }
            }
        }
    }
}
