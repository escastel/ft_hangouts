package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.data.models.Contact

@Composable
fun ContactCard(
    contact: Contact,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ContactAvatar(
                name = contact.firstName,
                imageUri = contact.imageUri,
                size = 50.dp,
                isUnknown = contact.firstName.firstOrNull()?.isDigit() == true || contact.firstName.startsWith("+")
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = contact.fullName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (contact.phoneNumber.isNotBlank()) {
                    Text(
                        text = contact.phoneNumber,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactCardPreview() {
    ContactCard(
        contact = Contact(
            id = 1,
            firstName = "First Name",
            lastName = "",
            phoneNumber = "6783291248",
            email = "",
            address = "",
            notes = "",
            imageUri = null,
        ),
        onClick = {})
}
