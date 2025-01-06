package com.example.android_project.classes

import androidx.compose.ui.graphics.Color
import com.example.android_project.classes.ui.theme.AvailableGreen
import com.example.android_project.classes.ui.theme.NotAvailableRed
import com.example.android_project.domain.model.FoodItem

import kotlin.random.Random

data class FoodVM(
    val id: Int = Random.nextInt(),
    val name: String="",
    val img: String= "placeholder",
    val price: Double=0.0,
    val category: FoodCategory= FoodCategory.GRILL,
    val course: Course = Course.MAIN,
    val availability: Availability =Available
) {
    fun toEntity(): FoodItem {
        val id = if(this.id==-1) null else this.id
        return FoodItem(
            id = id,
            name = this.name,
            img = this.img,
            price = this.price,
            category = this.category,
            course = this.course,
            availability = this.availability.toBoolean(),
        )
    }

    companion object {
        fun fromEntity(entity: FoodItem): FoodVM {
            return FoodVM(
                id=entity.id!!,
                name=entity.name,
                img = entity.img,
                price = entity.price,
                course = entity.course,
                category = entity.category,
                availability = Availability.fromBoolean(entity.availability)
            )
        }
    }
}

sealed class Availability(val backgroundColor: Color, val foreGroundColor: Color){
    fun toBoolean(): Boolean{
        return this is Available
    }

    companion object{
        fun fromBoolean(isAvailable:Boolean): Availability{
            return if (isAvailable) Available else NotAvailable
        }
    }
}

data object Available:Availability(AvailableGreen, Color.White)
data object NotAvailable:Availability(NotAvailableRed, Color.Black)

