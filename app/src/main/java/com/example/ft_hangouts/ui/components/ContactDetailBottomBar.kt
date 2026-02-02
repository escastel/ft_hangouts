package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun ContactDetailBottomBar(
    onCallClick: () -> Unit,
    onChatClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onCallClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_call),
                        contentDescription = stringResource(R.string.call),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onChatClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_email),
                        contentDescription = stringResource(R.string.sms),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onEditClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = stringResource(R.string.edit),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDeleteClick) {
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

@Preview
@Composable
fun ContactDetailBottomBarPreview(){
    Ft_hangoutsTheme {
        ContactDetailBottomBar(
            onCallClick = {},
            onChatClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}