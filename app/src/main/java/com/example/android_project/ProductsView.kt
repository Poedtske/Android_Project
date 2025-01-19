package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_project.presentation.category.admin.AddEditCategoryScreen
import com.example.android_project.presentation.category.admin.AdminListCategoriesScreen
import com.example.android_project.presentation.components.getImageResource

import com.example.android_project.presentation.food.admin.AddEditFoodScreen
import com.example.android_project.presentation.food.admin.AdminListProductsScreen
import com.example.android_project.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter
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
                    "Products",
                    painterResource(id = R.drawable.food_filled),
                    painterResource(id = R.drawable.food_unfilled)),
                BottomNavigationItem(
                    Screen.CategoriesListScreen.route,
                    "Categories",
                    painterResource(id = R.drawable.category_filled),
                    painterResource(id = R.drawable.category_unfilled)
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
                                            painter = if (currentRoute == item.route) {
                                                item.selectedIcon
                                            } else {
                                                item.unselectedIcon
                                            },
                                            contentDescription = item.title,
                                            modifier = Modifier.size(24.dp) // Set the size of the icons
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
                        composable(route = Screen.CategoriesListScreen.route) {
                            AdminListCategoriesScreen(navController)
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
                        composable(
                            route = Screen.AddEditCategoryScreen.route + "?categoryId={categoryId}",
                            arguments = listOf(
                                navArgument(name = "categoryId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { navBackStackEntry ->

                            AddEditCategoryScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
