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
        firstName: String = contactFormUiState.firstName,
        lastName: String = contactFormUiState.lastName,
        phoneNumber: String = contactFormUiState.phoneNumber,
        email: String = contactFormUiState.email,
        address: String = contactFormUiState.address,
        notes: String = contactFormUiState.notes,
        imageUri: String? = contactFormUiState.imageUri
    ) {
        contactFormUiState = contactFormUiState.copy(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email,
            address = address,
            notes = notes,
            imageUri = imageUri,
            firstNameError = if (firstName.isNotBlank()) null else contactFormUiState.firstNameError,
            phoneNumberError = if (phoneNumber.isNotBlank()) null else contactFormUiState.phoneNumberError
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
                        firstName = it.firstName,
                        lastName = it.lastName,
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

    private fun validate(): Boolean {
        val firstNameBlank = contactFormUiState.firstName.isBlank()
        val phoneBlank = contactFormUiState.phoneNumber.isBlank()
        
        contactFormUiState = contactFormUiState.copy(
            firstNameError = if (firstNameBlank) "First name is required" else null,
            phoneNumberError = if (phoneBlank) "Phone number is required" else null
        )
        
        return !firstNameBlank && !phoneBlank
    }

    fun onSaveContact(context: Context, onSuccess: () -> Unit) {
        if (!validate()) {
            Toast.makeText(context, context.getString(R.string.error_required_fields), Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val newContact = Contact(
                firstName = contactFormUiState.firstName,
                lastName = contactFormUiState.lastName,
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
        if (!validate()) {
            Toast.makeText(context, context.getString(R.string.error_required_fields), Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val updatedContact = Contact(
                id = contactId,
                firstName = contactFormUiState.firstName,
                lastName = contactFormUiState.lastName,
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
