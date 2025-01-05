package com.example.android_project.classes

enum class FoodCategory(val displayName: String) {
    SALADS("@string/category_salads"),
    SOUPS("@string/category_soups"),
    SEAFOOD("@string/category_seafood"),
    PASTA("@string/category_pasta"),
    GRILL("@string/category_grill");

    // Simplified comparison function
    fun isType(type: FoodCategory): Boolean {
        return this == type
    }
}
