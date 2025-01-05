package com.example.android_project.classes

enum class Course(val displayName: String) {
    PRE("Starter"),
    MAIN("Main Course"),
    DESSERT("Dessert");

    // Simplified comparison function
    private fun isType(type: Course): Boolean {
        return this == type
    }

    // Example usage for specific checks
    fun isPre() = isType(PRE)
    fun isMain() = isType(MAIN)
    fun isDessert() = isType(DESSERT)
}
