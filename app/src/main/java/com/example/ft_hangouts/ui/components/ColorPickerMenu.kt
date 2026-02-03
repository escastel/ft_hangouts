package com.example.ft_hangouts.ui.components

import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.greenPrimaryLight
import com.example.ft_hangouts.ui.theme.pinkPrimaryLight
import com.example.ft_hangouts.ui.theme.purplePrimaryLight
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
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
            ColorMenuItem(name = stringResource(R.string.color_purple), color = purplePrimaryLight, onClick = {
                onColorSelected(purplePrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = stringResource(R.string.color_blue), color = bluePrimaryLight, onClick = {
                onColorSelected(bluePrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = stringResource(R.string.color_green), color = greenPrimaryLight, onClick = {
                onColorSelected(greenPrimaryLight)
                expanded = false
            })

            ColorMenuItem(name = stringResource(R.string.color_pink), color = pinkPrimaryLight, onClick = {
                onColorSelected(pinkPrimaryLight)
                expanded = false
            })
        }
    }
}

@Preview
@Composable
fun ColorPickerMenuPreview(){
    Ft_hangoutsTheme {
        ColorPickerMenu(onColorSelected = {})
    }
}