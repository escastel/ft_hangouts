package com.example.ft_hangouts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun InfoRow(
    label: String,
    value: String,
    textColor: Color = Color.Unspecified
) {
    if (value.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = textColor.copy(alpha = 0.7f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor
            )
            Divider(color = textColor.copy(alpha = 0.2f), thickness = 0.5.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoRowPreview(){
    Ft_hangoutsTheme {
        InfoRow(
            label = stringResource(R.string.label_email),
            value = "example@gmail.com"
        )
    }
}