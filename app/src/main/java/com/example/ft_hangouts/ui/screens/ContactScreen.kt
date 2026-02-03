package com.example.ft_hangouts.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.components.ContactCard
import com.example.ft_hangouts.viewmodel.ContactViewModel

@Composable
fun ContactScreen(
    onCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = viewModel(),
) {
    val uiState = viewModel.contactListUiState

    LaunchedEffect(Unit) {
        viewModel.loadContacts()
    }

    Box(modifier = modifier.fillMaxSize().padding(vertical = 8.dp)) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (uiState.contacts.isEmpty()) {
            Text(
                text = stringResource(R.string.no_contacts),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Gray
            )
        } else {
            LazyColumn {
                items(uiState.contacts) { contact ->
                    ContactCard(
                        contact = contact,
                        onClick = {
                            onCardClick(contact.id)
                        }
                    )
                }
            }
        }
    }
}
