package com.example.android_project.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_project.classes.FoodVM


@Composable
fun SortOptions(
    foodOrder: SortOrder = SortByName,
    onSortOrderChange: (SortOrder) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        FoodRadioButton(text = "Name",
            selected = foodOrder is SortByName,
            onSelect = { onSortOrderChange(SortByName) })

        Spacer(modifier = Modifier.width(8.dp))

        FoodRadioButton(text = "Price",
            selected = foodOrder is SortByPrice,
            onSelect = { onSortOrderChange(SortByPrice) })

    }
    Spacer(modifier = Modifier.height(8.dp))

}

sealed class SortOrder()
data object SortByName : SortOrder()
data object SortByPrice : SortOrder()

data class NotesState(
    val foodVM: List<FoodVM> = emptyList(),
    val foodOrder: SortOrder = SortByName,
)

sealed class FoodEvent {
    data class Order(val order: SortOrder):FoodEvent()
    data class Delete(val foodVM: FoodVM):FoodEvent()
}