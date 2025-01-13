package com.example.android_project.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project.classes.FoodVM
import com.example.android_project.domain.model.FoodItem
import com.example.android_project.presentation.components.FoodEvent
import com.example.android_project.presentation.components.SortByName
import com.example.android_project.presentation.components.SortByPrice
import com.example.android_project.presentation.components.SortOrder
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AdminListProductViewModel(val database: FirebaseDatabase): ViewModel() {
    private val _food: MutableState<List<FoodVM>> = mutableStateOf(emptyList())
    var food: State<List<FoodVM>> =_food

    private var _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByName)
    var sortOrder: State<SortOrder> = _sortOrder

    var job: Job?=null

    init {
        loadFood(sortOrder.value)
    }


    private fun loadFood(sortOrder: SortOrder) {
        viewModelScope.launch {
            try {
                val databaseReference = database.getReference("food")
                val snapshot = databaseReference.get().await() // Use suspension function

                val foodList = snapshot.children.mapNotNull { childSnapshot ->
                    val foodItem = childSnapshot.getValue(FoodItem::class.java)
                    foodItem?.let { FoodVM.fromEntity(it) }
                }

                _food.value = foodList
                sortFood(sortOrder)
            } catch (e: Exception) {
                Log.e("Firebase", "Error fetching data", e)
            }
        }
    }


    private fun sortFood(order: SortOrder){
        _sortOrder.value=order

        _food.value = when (order){
            SortByName -> food.value.sortedBy { it.name }
            SortByPrice -> food.value.sortedBy { it.price }
        }
    }

    fun onEvent(event: FoodEvent){
        when(event){
            is FoodEvent.Delete -> {
                deleteFood(event.foodVM)
                loadFood(sortOrder.value)
            }
            is FoodEvent.Order -> {
                _sortOrder.value = event.order
                loadFood(event.order)
            }
        }
    }

    private fun deleteFood(foodVM: FoodVM) {
        viewModelScope.launch {
            try {
                database.getReference("food")
                    .child("${foodVM.id}") // Assuming FoodVM has an `id` property
                    .removeValue()
                    .await()
            } catch (e: Exception) {
                Log.e("Firebase", "Error deleting data", e)
            }
        }
    }
}