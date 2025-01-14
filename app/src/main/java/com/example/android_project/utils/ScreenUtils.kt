package com.example.android_project.utils

sealed class Screen(val route: String) {
    data object FoodListScreen: Screen("food_list_screen")
    data object AddEditFoodScreen: Screen("add_edit_food_screen")
    data object OberTableScreen: Screen("ober_table_view")
}