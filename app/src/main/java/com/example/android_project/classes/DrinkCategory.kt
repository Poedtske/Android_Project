package com.example.android_project.classes

enum class DrinkCategory(val displayName: String) {
    SODA("@string/category_soda"),
    BEER("@string/category_beer"),
    WINE("@string/category_wine"),
    CHAMPAGNE("@string/category_champagne");

    // Simplified comparison function
    fun isType(type: DrinkCategory): Boolean {
        return this == type
    }
}
