package com.example.android_project.data.model

import com.example.android_project.classes.Availability
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM

data class FoodEntity(
    val id: Int,
    val name: String,
    val img: String,
    val price: Double,
    val category: CategoryVM? = null,
    val available: Boolean // Map this properly
){
    fun toVM(): FoodVM {
        //val id = if(this.id==-1) null else this.id
        return FoodVM(
            id = id,
            name = this.name,
            img = this.img,
            price = this.price,
            category = this.category ?: CategoryVM.placeholder(),
            availability = Availability.fromBoolean(available),
        )
    }
}