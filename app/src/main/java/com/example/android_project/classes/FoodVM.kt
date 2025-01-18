package com.example.android_project.classes

import androidx.compose.ui.graphics.Color
import com.example.android_project.classes.ui.theme.AvailableGreen
import com.example.android_project.classes.ui.theme.NotAvailableRed
import com.example.android_project.data.model.FoodEntity

data class FoodVM(
    val id: Int = -1,
    val name: String="",
    val img: String= "placeholder",
    val price: Double=0.0,
    val category: CategoryVM = CategoryVM.placeholder(),
    val availability: Availability =Available
) {

    fun toEntity() = FoodEntity(
        id = this.id,
        name = this.name,
        img = this.img,
        price = this.price,
        category = this.category,
        available = this.availability is Available
    )

}

sealed class Availability(
    val backgroundColor: Color,
    val foreGroundColor: Color,
    val isAvailable: Boolean // Add this property
) {
    fun toBoolean(): Boolean {
        return isAvailable
    }

    companion object {
        fun fromBoolean(isAvailable: Boolean): Availability {
            return if (isAvailable) Available else NotAvailable
        }
    }
}

data object Available : Availability(AvailableGreen, Color.White, true)
data object NotAvailable : Availability(NotAvailableRed, Color.Black, false)



