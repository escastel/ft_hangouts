package com.example.ft_hangouts.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.data.models.Message
import com.example.ft_hangouts.ui.state.ChatUiState
import com.example.ft_hangouts.ui.state.ConversationListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = AppDatabase(application)

    var conversationListUiState by mutableStateOf(ConversationListUiState())
        private set

    var chatUiState by mutableStateOf(ChatUiState())
        private set

    fun loadConversations() {
        conversationListUiState = conversationListUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbHelper.getActiveConversations()
            withContext(Dispatchers.Main) {
                conversationListUiState = ConversationListUiState(conversations = list, isLoading = false)
            }
        }
    }

    fun loadMessages(contactId: Long) {
        chatUiState = chatUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val msgList = dbHelper.getMessagesForContact(contactId)
            val contact = dbHelper.getContactById(contactId)
            withContext(Dispatchers.Main) {
                chatUiState = chatUiState.copy(
                    messages = msgList,
                    contact = contact,
                    isLoading = false
                )
            }
        }
    }

    fun updateChatInput(text: String) {
        chatUiState = chatUiState.copy(inputText = text)
    }

    fun onSendMessage(contactId: Long, context: Context, onPermissionNeeded: () -> Unit, onMessageSent: () -> Unit) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            sendMessageInternal(contactId, context, onMessageSent)
        } else {
            onPermissionNeeded()
        }
    }

    private fun sendMessageInternal(contactId: Long, context: Context, onMessageSent: () -> Unit) {
        val content = chatUiState.inputText
        if (content.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            val contact = dbHelper.getContactById(contactId)

            if (contact != null && contact.phoneNumber.isNotBlank()) {
                try {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(contact.phoneNumber, null, content, null, null)

                    val newMessage = Message(
                        contactId = contactId,
                        content = content,
                        timestamp = System.currentTimeMillis(),
                        isReceived = false
                    )
                    dbHelper.addMessage(newMessage)
                    
                    withContext(Dispatchers.Main) {
                        updateChatInput("")
                        loadMessages(contactId)
                        onMessageSent()
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val msg = context.getString(R.string.error_sms_send, e.message)
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, context.getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
