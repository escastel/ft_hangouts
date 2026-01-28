package com.example.ft_hangouts.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.R
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "ft_hangouts",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            //TODO: Acción del boton para añadir un contacto
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = ""
                )
            }
            //TODO: Accion del boton para desplegar el menu
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu),
                    contentDescription = ""
                )
            }
        },
    )
}

@Preview
@Composable
fun CustomTopBarPreview(){
    Ft_hangoutsTheme {
        CustomTopBar()
    }
}