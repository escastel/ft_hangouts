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
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.components.ContactAvatar
import com.example.ft_hangouts.ui.components.CustomTopBar
import com.example.ft_hangouts.ui.components.InfoRow
import com.example.ft_hangouts.viewmodel.AppViewModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ContactDetailScreen(
    contactId: Long,
    navController: NavController,
    viewModel: AppViewModel = viewModel()
) {
    LaunchedEffect(contactId) {
        viewModel.getContactById(contactId)
    }

    val contact = viewModel.selectedContact

    val isUnknown = contact?.name?.startsWith("+") == true ||
            contact?.name?.firstOrNull()?.isDigit() == true

    if (contact == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "",
                onBackClick = { navController.popBackStack() },
                onColorSelected = null
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    val context = LocalContext.current
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (contact.phoneNumber.isNotBlank()) {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${contact.phoneNumber}")
                                }
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, context.getString(R.string.error_no_phone), Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_call),
                                contentDescription = stringResource(R.string.call),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = { navController.navigate("chat/${contact.id}") }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_email),
                                contentDescription = stringResource(R.string.sms),
                                tint = MaterialTheme.colorScheme.primary)
                        }

                        IconButton(onClick = { navController.navigate("editContact/${contact.id}") }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_edit),
                                contentDescription = stringResource(R.string.edit),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(onClick = {
                            viewModel.deleteContact(contact.id)
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_delete),
                                contentDescription = stringResource(R.string.delete),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
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
                        name = contact.name,
                        imageUri = contact.imageUri,
                        size = 150.dp,
                        isUnknown = contact.name.firstOrNull()?.isDigit() == true || contact.name.startsWith("+")
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
                        text = contact.name.take(1).uppercase(),
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = contact.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = contact.phoneNumber, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            if (contact.email.isNotEmpty() && contact.email.isNotBlank() || contact.address.isNotEmpty() && contact.address.isNotBlank()|| contact.notes.isNotEmpty() && contact.notes.isNotBlank()){
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