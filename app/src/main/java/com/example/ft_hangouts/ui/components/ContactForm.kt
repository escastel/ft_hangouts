package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun ContactForm(
    name: String,
    onNameChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputField(
            value = name,
            onValueChange = onNameChange,
            label = stringResource(R.string.label_name),
            icon = R.drawable.ic_person,
            isRequired = true
        )

        InputField(
            value = phoneNumber,
            onValueChange = onPhoneChange,
            label = stringResource(R.string.label_phone),
            icon = R.drawable.ic_call,
            keyboardType = KeyboardType.Phone,
            isRequired = true
        )

        InputField(
            value = email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.label_email),
            icon = R.drawable.ic_email,
            keyboardType = KeyboardType.Email
        )

        InputField(
            value = address,
            onValueChange = onAddressChange,
            label = stringResource(R.string.label_address),
            icon = R.drawable.ic_location
        )

        InputField(
            value = notes,
            onValueChange = onNotesChange,
            label = stringResource(R.string.label_notes),
            icon = R.drawable.ic_note,
            singleLine = false,
            maxLines = 4
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ContactFormPreview(){
    Ft_hangoutsTheme {
        ContactForm(
            name = "",
            onNameChange = {},
            phoneNumber = "",
            onPhoneChange = {},
            email = "",
            onEmailChange = {},
            address = "",
            onAddressChange = {},
            notes = "",
            onNotesChange = {},
        )
    }
}