package com.example.ft_hangouts.viewmodel

import android.app.Application
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.data.models.Conversation
import com.example.ft_hangouts.data.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = AppDatabase(application)

    var contacts by mutableStateOf(listOf<Contact>())
        private set

    var selectedContact by mutableStateOf<Contact?>(null)
        private set

    var messages by mutableStateOf(listOf<Message>())
        private set

    var conversations by mutableStateOf(listOf<Conversation>())
        private set

    init {
        loadContacts()
    }

    fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbHelper.getAllContacts()
            withContext(Dispatchers.Main) {
                contacts = list
            }
        }
    }

    fun loadConversations() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbHelper.getActiveConversations()
            withContext(Dispatchers.Main) {
                conversations = list
            }
        }
    }

    fun loadMessages(contactId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val msgList = dbHelper.getMessagesForContact(contactId)
            withContext(Dispatchers.Main) {
                messages = msgList
            }
        }
    }

    fun sendMessage(contactId: Long, content: String, context: android.content.Context) {
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

                    loadMessages(contactId)

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

    fun addContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.createContact(contact)
            loadContacts()
        }
    }

    fun getContactById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = dbHelper.getContactById(id)
            withContext(Dispatchers.Main) {
                selectedContact = contact
            }
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.updateContact(contact)
            getContactById(contact.id)
        }
    }

    fun deleteContact(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.deleteContact(id)
            loadContacts()
        }
    }
}