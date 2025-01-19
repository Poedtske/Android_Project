package com.example.android_project.data.model;

import com.example.android_project.classes.Availability
import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.FoodVM

data class CategoryEntity (
        val id: Int,
        val name: String,
        val img: String,
        val products: List<FoodVM>
){
        fun toVM(): CategoryVM {
                //val id = if(this.id==-1) null else this.id
                return CategoryVM(
                        id = id,
                        name = this.name,
                        img = this.img,
                        products=this.products
                )
        }
}
