package com.example.ft_hangouts.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.example.ft_hangouts.data.models.Contact
import com.example.ft_hangouts.ui.components.AvatarInput
import com.example.ft_hangouts.ui.components.ContactForm
import com.example.ft_hangouts.ui.components.RowButtons
import com.example.ft_hangouts.viewmodel.AppViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun AddContactScreen(
    navController: NavController,
    viewModel: AppViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            RowButtons(
                buttonText = stringResource(R.string.save),
                onCancel = { navController.popBackStack() },
                onSave = {
                    if (name.isBlank() || phoneNumber.isBlank()) {
                        Toast.makeText(context, context.getString(R.string.error_required_fields), Toast.LENGTH_SHORT).show()
                    } else {
                        val newContact = Contact(
                            name = name,
                            phoneNumber = phoneNumber,
                            email = email,
                            address = address,
                            notes = notes,
                            imageUri = imageUri
                        )
                        viewModel.addContact(newContact)
                        Toast.makeText(context, context.getString(R.string.contact_saved), Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AvatarInput(
                name = name,
                imageUri = imageUri,
                onImageSelected = { newUri -> imageUri = newUri },
                onImageRemoved = { imageUri = "" },
                isEditMode = false)

            Spacer(modifier = Modifier.height(30.dp))

            ContactForm(
                name = name, onNameChange = { name = it },
                phoneNumber = phoneNumber, onPhoneChange = { phoneNumber = it },
                email = email, onEmailChange = { email = it },
                address = address, onAddressChange = { address = it },
                notes = notes, onNotesChange = { notes = it }
            )
        }
    }
}