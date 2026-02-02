package com.example.ft_hangouts.ui

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ft_hangouts.navigation.NavDestination
import com.example.ft_hangouts.navigation.NavigationGraph
import com.example.ft_hangouts.ui.components.BottomNavigation
import com.example.ft_hangouts.ui.components.CustomTopBar
import com.example.ft_hangouts.ui.components.FloatingButton
import com.example.ft_hangouts.ui.components.NavigationRailBar
import com.example.ft_hangouts.ui.theme.Ft_hangoutsTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.ft_hangouts.R


fun saveColor(context: Context, colorValue: ULong) {
    val sharedPref = context.getSharedPreferences("ft_hangouts_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putLong("theme_color", colorValue.toLong())
        apply()
    }
}

fun loadColor(context: Context): Color {
    val sharedPref = context.getSharedPreferences("ft_hangouts_prefs", Context.MODE_PRIVATE)
    val colorLong = sharedPref.getLong("theme_color", 0)
    return if (colorLong == 0L) Color.Unspecified else Color(value = colorLong.toULong())
}

@Composable
fun MainApp(windowSize: WindowSizeClass) {
    val navController = rememberNavController()
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MainAppPortrait(navController)
        }
        WindowWidthSizeClass.Expanded -> {
            MainAppLandscape(navController)
        }
        else -> {
            MainAppLandscape(navController)
        }
    }
}

@Composable
fun MainAppPortrait(navController: NavHostController) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var currentThemeColor by remember {
        mutableStateOf(loadColor(context))
    }

    Ft_hangoutsTheme(targetColor = currentThemeColor) {
        val isMainScreen = currentRoute == NavDestination.Contacts.route ||
                currentRoute == NavDestination.Conversations.route

        Scaffold(
            topBar = {
                if (isMainScreen) {
                    val title = if (currentRoute == NavDestination.Conversations.route)
                        stringResource(R.string.messages)
                    else
                        stringResource(R.string.contacts)

                    CustomTopBar(
                        title = title,
                        onBackClick = null,
                        onColorSelected = { newColor ->
                            currentThemeColor = newColor
                            saveColor(context, newColor.value)
                        }
                    )
                }
            },
            bottomBar = {
                if (isMainScreen) {
                    BottomNavigation(navController)
                }
            },
            floatingActionButton = {
                if (currentRoute == NavDestination.Contacts.route) {
                    FloatingButton(onClick = { navController.navigate(NavDestination.AddContact.route) })
                }
            }
        ) { padding ->
            NavigationGraph(
                navController = navController,
                padding = padding
            )
        }
    }
}

@Composable
fun MainAppLandscape(navController: NavHostController) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var currentThemeColor by remember {
        mutableStateOf(loadColor(context))
    }

    Ft_hangoutsTheme(targetColor = currentThemeColor) {
        val isMainScreen = currentRoute == NavDestination.Contacts.route ||
                currentRoute == NavDestination.Conversations.route

        Surface(color = MaterialTheme.colorScheme.background) {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRailBar(navController = navController)

                Scaffold(
                    topBar = {
                        if (isMainScreen) {
                            val title = if (currentRoute == NavDestination.Conversations.route)
                                stringResource(R.string.messages)
                            else
                                stringResource(R.string.contacts)

                            CustomTopBar(
                                title = title,
                                onBackClick = null,
                                onColorSelected = { newColor ->
                                    currentThemeColor = newColor
                                    saveColor(context, newColor.value)
                                }
                            )
                        }
                    },
                    floatingActionButton = {
                        if (currentRoute == NavDestination.Contacts.route) {
                            FloatingButton(onClick = {
                                navController.navigate(NavDestination.AddContact.route)
                            })
                        }
                    }
                ) { padding ->
                    NavigationGraph(
                        navController = navController,
                        padding = padding
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MainAppPortraitPreview() {
    val navController = rememberNavController()
    MainAppPortrait(navController)
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MainAppLandscapePreview() {
    val navController = rememberNavController()
    MainAppLandscape(navController)
}