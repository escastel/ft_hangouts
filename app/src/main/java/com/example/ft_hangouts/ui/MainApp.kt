package com.example.ft_hangouts.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.ui.components.BottomNavigation
import com.example.ft_hangouts.ui.components.CustomTopBar
import com.example.ft_hangouts.ui.components.FloatinButton
import com.example.ft_hangouts.ui.components.NavigationRailBar
import com.example.ft_hangouts.ui.screens.HomeScreen
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme

@Composable
fun MainApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MainAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MainAppLandscape()
        }
    }
}

@Composable
fun MainAppPortrait() {
    val navController = rememberNavController()
    Ft_hangoutsTheme {
        Scaffold(
            //TODO: Acciones de la barra de arriba
            topBar = { CustomTopBar() },
            bottomBar = { BottomNavigation(navController) },
            //TODO: Navegacion a la pantalla de añadir contacto
            floatingActionButton = { FloatinButton({}) }
        ) { padding ->
            HomeScreen(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun MainAppLandscape() {
    val navController = rememberNavController()
    Ft_hangoutsTheme {
        Scaffold(
            //TODO: Acciones de la barra de arriba
            topBar = { CustomTopBar() },
            //TODO: Navegacion a la pantalla de añadir contacto
            floatingActionButton = { FloatinButton({}) }
        ) { padding ->
            Surface {
                Row {
                    NavigationRailBar(navController)
                    HomeScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MainAppPortraitPreview() {
    MainAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MainAppLandscapePreview() {
    MainAppLandscape()
}