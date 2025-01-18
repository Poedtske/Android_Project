package com.example.android_project.classes

import kotlin.random.Random

data class CategoryVM(
    val id: Int = -1,
    val name: String="",
    val img: String= "placeholder",
    val products: List<FoodVM> = emptyList()
) {
    companion object {
        fun placeholder() = CategoryVM()
    }
}