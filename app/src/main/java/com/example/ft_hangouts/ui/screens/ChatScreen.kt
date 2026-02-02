package com.example.ft_hangouts.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.components.CustomTopBar
import com.example.ft_hangouts.ui.components.MessageBubble
import com.example.ft_hangouts.ui.components.ChatInputBar
import com.example.ft_hangouts.viewmodel.AppViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ChatScreen(
    contactId: Long,
    navController: NavController,
    viewModel: AppViewModel = viewModel()
) {
    val uiState = viewModel.chatUiState
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()

    LaunchedEffect(contactId) {
        viewModel.loadMessages(contactId)
    }

    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.size - 1)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ -> }

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == "com.example.ft_hangouts.REFRESH_CHAT") {
                    viewModel.loadMessages(contactId)
                }
            }
        }

        val filter = IntentFilter("com.example.ft_hangouts.REFRESH_CHAT")
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = uiState.contact?.name ?: stringResource(R.string.chat_default_title),
                onBackClick = { navController.popBackStack() },
                onColorSelected = null
            )
        },
        bottomBar = {
            ChatInputBar(
                text = uiState.inputText,
                onTextChange = { viewModel.updateChatInput(it) },
                onSendClick = {
                    viewModel.onSendMessage(
                        contactId = contactId,
                        context = context,
                        onPermissionNeeded = {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.SEND_SMS,
                                    Manifest.permission.RECEIVE_SMS
                                )
                            )
                        },
                        onMessageSent = {
                            focusManager.clearFocus()
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.messages) { message ->
                    MessageBubble(message = message)
                }
            }
        }
    }
}
