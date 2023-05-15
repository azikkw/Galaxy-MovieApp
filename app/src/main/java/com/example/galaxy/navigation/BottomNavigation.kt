package com.example.galaxy.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.galaxy.R

@Composable
fun BottomNavigation (
    navController: NavController
) {
    val screens = listOf (
        BottomNavItem.Home,
        BottomNavItem.MoviesList,
        BottomNavItem.Favorites,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    BottomNavigation (
        backgroundColor = Color(51, 73, 109, 255),
        contentColor = Color(255, 255, 255, 250),
        modifier = Modifier
            .height(70.dp)
            .clip(shape = RoundedCornerShape(topStart = 21.dp, topEnd = 21.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 5.dp),
                icon = {
                    Icon (
                        screen.Icon,
                        contentDescription = screen.title,
                        modifier = if(screen.title == "Movies") Modifier.size(27.dp) else if(screen.title == "Profile") Modifier.size(27.dp) else if(screen.title == "Saved") Modifier.size(27.dp) else Modifier.size(25.dp)
                    )
                },
                label = {
                    Text (
                        text = screen.title,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.quicksandbold)),
                    )
                },
                selectedContentColor = Color(255, 255, 255),
                unselectedContentColor = Color(255, 255, 255, 180),
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}