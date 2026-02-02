package com.example.ft_hangouts.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.database.AppDatabase
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.ui.state.ContactUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactFormViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = AppDatabase(application)

    var contactFormUiState by mutableStateOf(ContactUiState())
        private set

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

    fun setContactForEdit(contactId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val contact = dbHelper.getContactById(contactId)
            withContext(Dispatchers.Main) {
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
                onSuccess()
            }
        }
    }
}
