package com.example.ft_hangouts.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.data.database.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = ContactDatabase(application)

    var contacts by mutableStateOf(listOf<Contact>())
        private set

    var selectedContact by mutableStateOf<Contact?>(null)
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