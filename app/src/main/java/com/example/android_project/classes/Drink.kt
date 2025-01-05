package com.example.android_project.classes

import java.util.UUID

data class Drink(
    val id: UUID = UUID.randomUUID(),  // Automatically generates a unique ID
    var name: String,                  // Product name
    var img: String,                   // Image URL or file path
    var category: DrinkCategory,            // Category of the product
    var course: Course,
    var price: Double                  // Price of the product
)