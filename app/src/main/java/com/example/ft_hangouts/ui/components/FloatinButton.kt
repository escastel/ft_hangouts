package com.example.ft_hangouts.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun FloatinButton(onClick: () -> Unit) {
    SmallFloatingActionButton(onClick = onClick) {
        Icon(
            painterResource(R.drawable.ic_add),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun FloatingButtonPreview(){
    Ft_hangoutsTheme {
        FloatinButton( onClick = {} )
    }
}