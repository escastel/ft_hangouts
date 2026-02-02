package com.example.ft_hangouts.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.ui.state.ContactDetailUiState
import com.example.ft_hangouts.ui.state.ContactListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = AppDatabase(application)

    var contactListUiState by mutableStateOf(ContactListUiState())
        private set

    var contactDetailUiState by mutableStateOf(ContactDetailUiState())
        private set

    fun loadContacts() {
        contactListUiState = contactListUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val list = dbHelper.getAllContacts()
            withContext(Dispatchers.Main) {
                contactListUiState = ContactListUiState(contacts = list, isLoading = false)
            }
        }
    }

    fun getContactById(id: Long) {
        contactDetailUiState = contactDetailUiState.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val contact = dbHelper.getContactById(id)
            withContext(Dispatchers.Main) {
                contactDetailUiState = ContactDetailUiState(contact = contact, isLoading = false)
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
}
