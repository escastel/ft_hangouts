package com.example.ft_hangouts.viewmodel

import android.app.Application
import android.content.Context
import android.util.Patterns
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
            phoneNumberError = if (phoneNumber.isNotBlank()) null else contactFormUiState.phoneNumberError,
            emailError = if (email.isNotBlank()) null else contactFormUiState.emailError
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
        val uiState = contactFormUiState
        val firstNameError = if (uiState.firstName.isBlank()) R.string.error_required_fields else null
        
        val phoneNumberError = when {
            uiState.phoneNumber.isBlank() -> R.string.error_required_fields
            !Patterns.PHONE.matcher(uiState.phoneNumber).matches() -> R.string.error_invalid_phone
            else -> null
        }

        val emailError = if (uiState.email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) {
            R.string.error_invalid_email
        } else null

        contactFormUiState = uiState.copy(
            firstNameError = firstNameError,
            phoneNumberError = phoneNumberError,
            emailError = emailError
        )

        return firstNameError == null && phoneNumberError == null && emailError == null
    }

    fun onSaveContact(context: Context, onSuccess: () -> Unit) {
        if (!validate()) {
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
