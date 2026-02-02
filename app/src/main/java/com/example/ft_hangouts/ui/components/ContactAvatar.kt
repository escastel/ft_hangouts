package com.example.ft_hangouts.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import java.io.File

@Composable
fun ContactAvatar(
    modifier: Modifier = Modifier,
    name: String,
    imageUri: String?,
    size: Dp = 120.dp,
    isUnknown: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
    ) {
        when {
            !imageUri.isNullOrBlank() -> {
                val bitmap = remember(imageUri) {
                    try {
                        val file = File(imageUri)
                        if (file.exists()) {
                            BitmapFactory.decodeFile(imageUri)
                        } else {
                            null
                        }
                    } catch (_: Exception) {
                        null
                    }
                }

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = stringResource(R.string.contact_image),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = name.take(1).uppercase(),
                        fontSize = (size.value * 0.4f).sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            isUnknown -> {
                Icon(
                    painter = painterResource(R.drawable.ic_person),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(size * 0.15f)
                        .size(size * 0.5f)
                )
            }
            else -> {
                Text(
                    text = name.take(1).uppercase(),
                    fontSize = (size.value * 0.4f).sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ContactAvatarPreview(){
    Ft_hangoutsTheme {
        ContactAvatar(
            name = "Name",
            imageUri = null,
            size = 120.dp,
            isUnknown = true
        )
    }
}