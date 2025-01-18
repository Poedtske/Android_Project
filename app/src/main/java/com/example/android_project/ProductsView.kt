package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room

import com.example.android_project.presentation.AddEditFoodScreen
import com.example.android_project.presentation.AdminListProductsScreen
import com.example.android_project.utils.Screen
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@AndroidEntryPoint
class ProductsView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val items = listOf(
                BottomNavigationItem(
                    Screen.FoodListScreen.route,
                    "Home",
                    Icons.Filled.Home,
                    Icons.Outlined.Home
                )
            )
            com.example.android_project.ui.theme.Android_ProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            // Observe the current back stack entry
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            items.forEach { item ->
                                NavigationBarItem(
                                    selected = currentRoute == item.route,
                                    onClick = {
                                        if (currentRoute != item.route) {
                                            navController.navigate(item.route) {
                                                popUpTo(Screen.FoodListScreen.route) {
                                                    inclusive = true // Clears the navigation stack
                                                }
                                                launchSingleTop = true // Avoids multiple copies of the same destination
                                            }
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (currentRoute == item.route) {
                                                item.selectedIcon
                                            } else {
                                                item.unselectedIcon
                                            },
                                            contentDescription = item.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.FoodListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.FoodListScreen.route) {
                            AdminListProductsScreen(navController)
                        }
                        composable(
                            route = Screen.AddEditFoodScreen.route + "?foodId={foodId}",
                            arguments = listOf(
                                navArgument(name = "foodId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { navBackStackEntry ->

                            AddEditFoodScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
