package com.example.android_project.data.model

import com.example.android_project.classes.CategoryVM
import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.FoodVM
import com.example.android_project.classes.OrderVM
import java.util.UUID
import kotlin.random.Random

data class OrderEntity(
    val id: Int = -1,
    val client: ClientVM = ClientVM.placeholder(),
    val products: List<FoodVM> = emptyList()
){
    fun toVM(): OrderVM {
        //val id = if(this.id==-1) null else this.id
        return OrderVM(
            id = this.id,
            client = this.client,
            products=this.products
        )
    }
}