package com.example.android_project.classes

enum class Course(val displayName: String) {
    NONE("None"),
    STARTER("Starter"),
    MAIN("Main Course"),
    DESSERT("Dessert");

    // Simplified comparison function
    private fun isType(type: Course): Boolean {
        return this == type
    }
}
