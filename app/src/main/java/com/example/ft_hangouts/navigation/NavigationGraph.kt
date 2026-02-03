package com.example.ft_hangouts.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ft_hangouts.ui.screens.AddContactScreen
import com.example.ft_hangouts.ui.screens.ConversationScreen
import com.example.ft_hangouts.ui.screens.ChatScreen
import com.example.ft_hangouts.ui.screens.ContactDetailScreen
import com.example.ft_hangouts.ui.screens.ContactScreen
import com.example.ft_hangouts.ui.screens.EditContactScreen

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
            ContactScreen(
                onCardClick = { id ->
                    navController.navigate(NavDestination.ContactDetail.createRoute(id))
                },
            )
        }
        composable(route = NavDestination.Conversations.route){
            ConversationScreen(navController = navController)
        }
        composable(route = NavDestination.AddContact.route){
            AddContactScreen(navController)
        }
        composable(
            route = NavDestination.EditContact.route,
            arguments = listOf(navArgument("editContactId") { type = NavType.LongType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getLong("editContactId") ?: 0L
            EditContactScreen(contactId = contactId, navController = navController)
        }
        composable(
            route = NavDestination.Chat.route,
            arguments = listOf(navArgument("chatContactId") { type = NavType.LongType })
        ){ backStackEntry ->
            val contactId = backStackEntry.arguments?.getLong("chatContactId") ?: 0L
            ChatScreen(contactId = contactId, navController = navController)
        }
        composable(
            route = NavDestination.ContactDetail.route,
            arguments = listOf(navArgument("detailContactId") { type = NavType.LongType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getLong("detailContactId") ?: 0L
            ContactDetailScreen(contactId = contactId, navController = navController)
        }
    }
}
