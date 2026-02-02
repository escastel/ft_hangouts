package com.example.ft_hangouts.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.ft_hangouts.R

sealed class TopLevelDestination(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    object Contacts: TopLevelDestination(
        route = NavDestination.Contacts.route,
        icon = R.drawable.ic_person,
        label = R.string.contacts
    )

    object Conversations: TopLevelDestination(
        route = NavDestination.Conversations.route,
        icon = R.drawable.ic_message,
        label = R.string.messages
    )
}

val topLevelDestinations = listOf(
    TopLevelDestination.Contacts,
    TopLevelDestination.Conversations
)