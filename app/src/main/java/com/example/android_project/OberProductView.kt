package com.example.android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.android_project.data.source.RestaurantDatabase
import com.example.android_project.presentation.AddEditFoodScreen
import com.example.android_project.presentation.AddEditFoodViewModel
import com.example.android_project.presentation.AdminListProductViewModel
import com.example.android_project.presentation.AdminListProductsScreen
import com.example.android_project.utils.Screen

class OberProductView : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            RestaurantDatabase::class.java,
            RestaurantDatabase.DATABASE_NAME
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            com.example.android_project.ui.theme.Android_ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FoodListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        //ViewModel
                        composable(route = Screen.FoodListScreen.route) {
                            val food = viewModel<AdminListProductViewModel> {
                                AdminListProductViewModel(db.foodDao)
                            }
                            AdminListProductsScreen(food, navController)
                        }

                        //addEditOrder
                        composable(route = Screen.AddEditFoodScreen.route + "?foodId={foodId}",
                            arguments = listOf(
                                navArgument(name = "foodId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )) { navBackStackEntry ->
                            val foodId = navBackStackEntry.arguments?.getInt("foodId") ?: -1

                            val food = viewModel<AddEditFoodViewModel>() {
                                AddEditFoodViewModel(foodId, db.foodDao)
                            }
                            AddEditFoodScreen(navController, food)
                        }
                    }

                }
            }
        }
    }
}

//intent.getStringExtra("TABLE_ID").orEmpty(),