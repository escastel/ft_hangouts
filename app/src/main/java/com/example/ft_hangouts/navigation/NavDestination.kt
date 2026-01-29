package com.example.ft_hangouts.navigation

sealed class NavDestination(
    val route: String
) {
    object Contacts: NavDestination("contacts")

    object Conversations: NavDestination("conversations")

    object AddContact: NavDestination("addContact")

    object EditContact: NavDestination("editContact/{contactId}"){
        fun createRoute(contactId: Long): String {
            return "editContact/$contactId"
        }
    }

    object ContactDetail: NavDestination("contactDetail/{contactId}") {
        fun createRoute(contactId: Long): String {
            return "contactDetail/$contactId"
        }
    }

    object Chat: NavDestination("chat/{contactId}") {
        fun createRoute(contactId: Long): String {
            return "chat/$contactId"
        }
    }
}