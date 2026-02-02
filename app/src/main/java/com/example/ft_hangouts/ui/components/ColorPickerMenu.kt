package com.example.ft_hangouts.ui.components

import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.compose.greenPrimaryLight
import com.example.compose.pinkPrimaryLight
import com.example.compose.purplePrimaryLight
import com.example.ft_hangouts.ui.theme.bluePrimaryLight

@Composable
fun ColorPickerMenu(onColorSelected: (Color) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = stringResource(R.string.change_photo),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ColorMenuItem(name = "Purple", color = purplePrimaryLight, onClick = {
                onColorSelected(purplePrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = "Blue", color = bluePrimaryLight, onClick = {
                onColorSelected(bluePrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = "Green", color = greenPrimaryLight, onClick = {
                onColorSelected(greenPrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = "Pink", color = pinkPrimaryLight, onClick = {
                onColorSelected(pinkPrimaryLight)
                expanded = false
            })
        }
    }
}

@Composable
fun ColorMenuItem(name: String, color: Color, onClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(color, CircleShape)
                        .border(1.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = name)
            }
        },
        onClick = onClick
    )
}