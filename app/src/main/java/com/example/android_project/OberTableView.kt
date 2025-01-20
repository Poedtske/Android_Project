package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_project.presentation.category.admin.AddEditCategoryScreen
import com.example.android_project.presentation.category.admin.AdminListCategoriesScreen
import com.example.android_project.presentation.food.admin.AddEditFoodScreen
import com.example.android_project.presentation.food.admin.AdminListProductsScreen
import com.example.android_project.ui.theme.Android_ProjectTheme
import com.example.android_project.utils.Screen

//name = "${stringResource(R.string.table)} id= ${intent.getStringExtra("TABLE_ID").orEmpty()}"
class OberTableView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            com.example.android_project.ui.theme.Android_ProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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