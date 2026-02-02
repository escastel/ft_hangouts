package com.example.ft_hangouts.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.data.models.Message
import com.example.ft_hangouts.ui.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = AppDatabase(application)

    var contactListUiState by mutableStateOf(ContactListUiState())
        private set

    var conversationListUiState by mutableStateOf(ConversationListUiState())
        private set

    var contactDetailUiState by mutableStateOf(ContactDetailUiState())
        private set

    var chatUiState by mutableStateOf(ChatUiState())
        private set

    var contactFormUiState by mutableStateOf(ContactUiState())
        private set

    init {
        loadContacts()
    }


    fun loadContacts() {
        contactListUiState = contactListUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbHelper.getAllContacts()
            withContext(Dispatchers.Main) {
                contactListUiState = ContactListUiState(contacts = list, isLoading = false)
            }
        }
    }

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

    fun getContactById(id: Long) {
        contactDetailUiState = contactDetailUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val contact = dbHelper.getContactById(id)
            withContext(Dispatchers.Main) {
                contactDetailUiState = ContactDetailUiState(contact = contact, isLoading = false)
                contact?.let {
                    contactFormUiState = ContactUiState(
                        name = it.name,
                        phoneNumber = it.phoneNumber,
                        email = it.email,
                        address = it.address,
                        notes = it.notes,
                        imageUri = it.imageUri
                    )
                }
            }
        }
    }


    fun updateChatInput(text: String) {
        chatUiState = chatUiState.copy(inputText = text)
    }

    fun updateContactForm(
        name: String = contactFormUiState.name,
        phoneNumber: String = contactFormUiState.phoneNumber,
        email: String = contactFormUiState.email,
        address: String = contactFormUiState.address,
        notes: String = contactFormUiState.notes,
        imageUri: String? = contactFormUiState.imageUri
    ) {
        contactFormUiState = contactFormUiState.copy(
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            address = address,
            notes = notes,
            imageUri = imageUri
        )
    }
    
    fun resetContactForm() {
        contactFormUiState = ContactUiState()
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

    fun onSaveContact(context: Context, onSuccess: () -> Unit) {
        if (contactFormUiState.name.isBlank() || contactFormUiState.phoneNumber.isBlank()) {
            Toast.makeText(context, context.getString(R.string.error_required_fields), Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val newContact = Contact(
                name = contactFormUiState.name,
                phoneNumber = contactFormUiState.phoneNumber,
                email = contactFormUiState.email,
                address = contactFormUiState.address,
                notes = contactFormUiState.notes,
                imageUri = contactFormUiState.imageUri
            )
            dbHelper.createContact(newContact)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, context.getString(R.string.contact_saved), Toast.LENGTH_SHORT).show()
                loadContacts()
                onSuccess()
            }
        }
    }

    fun onUpdateContact(contactId: Long, context: Context, onSuccess: () -> Unit) {
        if (contactFormUiState.name.isBlank() || contactFormUiState.phoneNumber.isBlank()) {
            Toast.makeText(context, context.getString(R.string.error_required_fields), Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val updatedContact = Contact(
                id = contactId,
                name = contactFormUiState.name,
                phoneNumber = contactFormUiState.phoneNumber,
                email = contactFormUiState.email,
                address = contactFormUiState.address,
                notes = contactFormUiState.notes,
                imageUri = contactFormUiState.imageUri
            )
            dbHelper.updateContact(updatedContact)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, context.getString(R.string.contact_updated), Toast.LENGTH_SHORT).show()
                getContactById(contactId)
                loadContacts()
                onSuccess()
            }
        }
    }

    fun onDeleteContact(id: Long, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.deleteContact(id)
            withContext(Dispatchers.Main) {
                loadContacts()
                onSuccess()
            }
        }
    }

    fun onCallContact(phoneNumber: String, context: Context) {
        if (phoneNumber.isNotBlank()) {
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, context.getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, context.getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show()
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
