package com.example.android_project.classes

import java.util.UUID

abstract class Product{
    val id: UUID = UUID.randomUUID()  // Automatically generates a unique ID
    abstract var name: String                  // Product name
    abstract var img: String                   // Image URL or file path
    abstract var price: Double                  // Price of the product

}