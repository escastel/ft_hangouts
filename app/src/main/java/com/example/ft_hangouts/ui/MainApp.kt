package com.example.ft_hangouts.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ft_hangouts.ui.components.CustomTopBar
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
    Ft_hangoutsTheme {
        Scaffold(
            topBar = { CustomTopBar() }
        ) { padding ->
            HomeScreen(modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun MainAppLandscape() {
    Ft_hangoutsTheme {
        Scaffold(
            topBar = { CustomTopBar() }
        ) { padding ->
            HomeScreen(modifier = Modifier.padding(padding))
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