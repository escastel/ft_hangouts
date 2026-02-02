package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R

@Composable
fun ContactForm(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    firstNameError: String?,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    phoneNumberError: String?,
    email: String,
    onEmailChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        InputField(
            value = firstName,
            onValueChange = onFirstNameChange,
            label = stringResource(R.string.label_first_name),
            icon = R.drawable.ic_person,
            error = firstNameError
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = stringResource(R.string.label_last_name),
            icon = R.drawable.ic_person
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            value = phoneNumber,
            onValueChange = onPhoneChange,
            label = stringResource(R.string.label_phone),
            icon = R.drawable.ic_call,
            error = phoneNumberError
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.label_email),
            icon = R.drawable.ic_email
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            value = address,
            onValueChange = onAddressChange,
            label = stringResource(R.string.label_address),
            icon = R.drawable.ic_location
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            value = notes,
            onValueChange = onNotesChange,
            label = stringResource(R.string.label_notes),
            icon = R.drawable.ic_note,
            singleLine = false
        )
    }
}
