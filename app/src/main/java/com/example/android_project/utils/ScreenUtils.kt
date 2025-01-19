package com.example.android_project.utils

sealed class Screen(val route: String) {
    data object FoodListScreen: Screen("food_list_screen")
    data object AddEditFoodScreen: Screen("add_edit_food_screen")
    data object CategoriesListScreen: Screen("categories_list_screen")
    data object AddEditCategoryScreen: Screen("add_edit_category_screen")
    data object OberTableView: Screen("ober_table_view")
}