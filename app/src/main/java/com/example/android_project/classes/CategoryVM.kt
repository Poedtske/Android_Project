package com.example.android_project.classes

import com.example.android_project.data.model.CategoryEntity

data class CategoryVM(
    val id: Int = -1,
    val name: String="",
    val img: String= "placeholder",
    val products: List<FoodVM> = emptyList()
) {

    fun toEntity(): CategoryEntity {
        //val id = if(this.id==-1) null else this.id
        return CategoryEntity(
            id = id,
            name = this.name,
            img = this.img,
            products=this.products
        )
    }

    companion object {
        fun placeholder() = CategoryVM()
    }
}