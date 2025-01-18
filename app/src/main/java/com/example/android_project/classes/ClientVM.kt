package com.example.android_project.classes

import java.util.UUID
import kotlin.random.Random

data class ClientVM(
    val id: Int = Random.nextInt(),  // Automatically generates a unique ID
    var tableId: Int,
)