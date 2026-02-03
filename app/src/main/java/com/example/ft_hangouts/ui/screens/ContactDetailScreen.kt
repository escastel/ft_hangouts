package com.example.ft_hangouts.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import com.example.ft_hangouts.R
import com.example.ft_hangouts.navigation.NavDestination
import com.example.ft_hangouts.ui.components.ContactAvatar
import com.example.ft_hangouts.ui.components.CustomTopBar
import com.example.ft_hangouts.ui.components.InfoRow
import com.example.ft_hangouts.ui.components.ContactDetailBottomBar
import com.example.ft_hangouts.viewmodel.ContactViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ContactDetailScreen(
    contactId: Long,
    navController: NavController,
    viewModel: ContactViewModel = viewModel()
) {
    val uiState = viewModel.contactDetailUiState
    val contact = uiState.contact
    val context = LocalContext.current

    LaunchedEffect(contactId) {
        viewModel.getContactById(contactId)
    }

    if (uiState.isLoading || contact == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val isUnknown = contact.fullName.startsWith("+") ||
            contact.fullName.firstOrNull()?.isDigit() == true

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "",
                onBackClick = { navController.popBackStack() },
                onColorSelected = null
            )
        },
        bottomBar = {
            ContactDetailBottomBar(
                onCallClick = { viewModel.onCallContact(contact.phoneNumber, context) },
                onChatClick = { 
                    navController.navigate(NavDestination.Chat.createRoute(contact.id)) 
                },
                onEditClick = { 
                    navController.navigate(NavDestination.EditContact.createRoute(contact.id)) 
                },
                onDeleteClick = {
                    viewModel.onDeleteContact(contact.id) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                if (!contact.imageUri.isNullOrBlank()) {
                    ContactAvatar(
                        name = contact.firstName,
                        imageUri = contact.imageUri,
                        size = 150.dp,
                        isUnknown = contact.firstName.firstOrNull()?.isDigit() == true || contact.firstName.startsWith("+")
                    )
                } else if (isUnknown) {
                    Icon(
                        painter = painterResource(R.drawable.ic_person),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(10.dp).size(75.dp)
                    )
                } else {
                    Text(
                        text = contact.firstName.take(1).uppercase(),
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = contact.fullName, style = MaterialTheme.typography.headlineMedium)
            Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            if (contact.email.isNotBlank() || contact.address.isNotBlank() || contact.notes.isNotBlank()){
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        val contentColor = MaterialTheme.colorScheme.onPrimary

                        InfoRow(
                            label = stringResource(R.string.label_email),
                            value = contact.email,
                            textColor = contentColor
                        )
                        InfoRow(
                            label = stringResource(R.string.label_address),
                            value = contact.address,
                            textColor = contentColor
                        )
                        InfoRow(
                            label = stringResource(R.string.label_notes),
                            value = contact.notes,
                            textColor = contentColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
