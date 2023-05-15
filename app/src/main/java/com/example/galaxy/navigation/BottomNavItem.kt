package com.example.galaxy.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var Icon: ImageVector,
    var route: String
) {
    object Home: BottomNavItem("Home", Icons.Default.Home, "home")
    object MoviesList: BottomNavItem("Movies", Icons.Default.Menu, "movies-list")
    object Favorites: BottomNavItem("Saved", Icons.Default.Favorite, "favorites")
    object Profile: BottomNavItem("Profile", Icons.Default.Person, "profile")
    object Search: BottomNavItem("Search", Icons.Default.Search, "search")
}