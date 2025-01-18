package com.example.android_project.classes

import java.util.UUID
import kotlin.random.Random

data class DrinkVM(
    val id: Int = Random.nextInt(),  // Automatically generates a unique ID
    var name: String,
    var img: String,
    var category: DrinkCategory,
    var availability: Availability,
    var price: Double,
)