package com.example.ft_hangouts.ui.screens

import android.annotation.SuppressLint
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
import com.example.ft_hangouts.ui.components.AvatarInput
import com.example.ft_hangouts.ui.components.ContactForm
import com.example.ft_hangouts.ui.components.RowButtons
import com.example.ft_hangouts.viewmodel.ContactFormViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun EditContactScreen(
    contactId: Long,
    navController: NavController,
    viewModel: ContactFormViewModel = viewModel()
) {
    val context = LocalContext.current
    val formState = viewModel.contactFormUiState

    LaunchedEffect(contactId) {
        viewModel.setContactForEdit(contactId)
    }

    Scaffold(
        bottomBar = {
            RowButtons(
                buttonText = stringResource(R.string.update),
                onCancel = { navController.popBackStack() },
                onSave = {
                    viewModel.onUpdateContact(contactId, context) {
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
                name = formState.firstName,
                imageUri = formState.imageUri,
                onImageSelected = { newUri -> viewModel.updateContactForm(imageUri = newUri) },
                onImageRemoved = { viewModel.updateContactForm(imageUri = null) },
                isEditMode = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            ContactForm(
                firstName = formState.firstName,
                onFirstNameChange = { viewModel.updateContactForm(firstName = it) },
                firstNameError = formState.firstNameError?.let { stringResource(it) },
                lastName = formState.lastName,
                onLastNameChange = { viewModel.updateContactForm(lastName = it) },
                phoneNumber = formState.phoneNumber,
                onPhoneChange = { viewModel.updateContactForm(phoneNumber = it) },
                phoneNumberError = formState.phoneNumberError?.let { stringResource(it) },
                email = formState.email,
                onEmailChange = { viewModel.updateContactForm(email = it) },
                emailError = formState.emailError?.let { stringResource(it) },
                address = formState.address,
                onAddressChange = { viewModel.updateContactForm(address = it) },
                notes = formState.notes,
                onNotesChange = { viewModel.updateContactForm(notes = it) }
            )
        }
    }
}
