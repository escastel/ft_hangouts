package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.purplePrimaryLight
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun ColorMenuItem(
    name: String,
    color: Color,
    onClick: () -> Unit
) {
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

@Preview (showBackground = true)
@Composable
fun ColorMenuItemPreview(){
    Ft_hangoutsTheme {
        ColorMenuItem(
            name = "Purple",
            color = purplePrimaryLight,
            onClick = {}
        )
    }
}