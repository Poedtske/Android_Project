package com.example.android_project.classes

import java.util.UUID
import kotlin.random.Random

data class OrderVM(
    val id: Int = Random.nextInt(),
    var clientId: Int,
)