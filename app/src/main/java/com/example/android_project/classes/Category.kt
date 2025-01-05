package com.example.android_project.classes

enum class Category(val displayName: String) {
    FOOD("Food"),
    DRINK("Drink");

    // Simplified comparison function
    private fun isType(type: Category): Boolean {
        return this == type
    }

    // Example usage for specific checks
    fun isDrink() = isType(DRINK)
    fun isFood() = isType(FOOD)
}