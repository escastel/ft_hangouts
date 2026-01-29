package com.example.ft_hangouts.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ft_hangouts.ui.screens.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    padding: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Contacts.route,
        modifier = Modifier.padding(padding)
    ){
        composable(route = NavDestination.Contacts.route){
            HomeScreen()
        }
        composable(route = NavDestination.Conversations.route){}
        composable(route = NavDestination.AddContact.route){}
        composable(route = NavDestination.EditContact.route){}
        composable(route = NavDestination.ContactDetail.route){}
        composable(route = NavDestination.Chat.route){}
    }
}