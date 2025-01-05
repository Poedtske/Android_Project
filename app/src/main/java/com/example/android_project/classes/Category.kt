package com.example.android_project.classes

enum class Category(val displayName: String) {
    SODA("@string/category_soda"),
    BEER("@string/category_beer"),
    WINE("@string/category_wine"),
    CHAMPAGNE("@string/category_champagne"),
    SNACKS("@string/category_snacks"),
    DESSERTS("@string/category_desserts"),
    APPETIZERS("@string/category_appetizers"),
    MAIN_COURSE("@string/category_main_course"),
    SALADS("@string/category_salads"),
    SOUPS("@string/category_soups"),
    SEAFOOD("@string/category_seafood"),
    PASTA("@string/category_pasta"),
    GRILL("@string/category_grill");

    // Simplified comparison function
    fun isType(type: Category): Boolean {
        return this == type
    }
}
