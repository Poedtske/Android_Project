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
import com.example.android_project.data.source.FoodDatabase
import com.example.android_project.presentation.AddEditFoodViewModel
import com.example.android_project.presentation.ListFoodViewModel
import com.example.android_project.presentation.ListProductsScreen
import com.example.android_project.presentation.AddEditFoodScreen
import com.example.android_project.ui.theme.Android_ProjectTheme
import com.example.android_project.utils.Screen

class MainActivity : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            FoodDatabase::class.java,
            FoodDatabase.DATABASE_NAME
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_ProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController=navController,
                        startDestination = Screen.FoodListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(route=Screen.FoodListScreen.route){
                            val food = viewModel<ListFoodViewModel>{
                                ListFoodViewModel(db.foodDao)
                            }
                            ListProductsScreen(food,navController)
                        }
                        composable(route = Screen.AddEditFoodScreen.route+"?foodId={foodId}", arguments = listOf(
                            navArgument(name = "foodId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )){ navBackStackEntry ->
                            val foodId = navBackStackEntry.arguments?.getInt("foodId") ?: -1

                            val food= viewModel<AddEditFoodViewModel>(){
                                AddEditFoodViewModel(foodId,db.foodDao)
                            }
                            AddEditFoodScreen(navController,food)
                        }
                    }

                }
            }
        }
    }
}

