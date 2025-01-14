package com.example.android_project.classes

import com.example.android_project.R

enum class FoodCategory(val displayName: Int) {
    SALADS(R.string.category_salads),
    SOUPS(R.string.category_soups),
    SEAFOOD(R.string.category_seafood),
    PASTA(R.string.category_pasta),
    GRILL(R.string.category_grill),
    UNKNOWN(R.string.unknown);

    // Simplified comparison function
    fun isType(type: FoodCategory): Boolean {
        return this == type
    }
}
