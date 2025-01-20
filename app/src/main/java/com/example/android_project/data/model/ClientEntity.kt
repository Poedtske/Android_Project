package com.example.android_project.data.model

import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.OrderVM
import com.example.android_project.classes.TableVM

data class ClientEntity(
    val id: Int = -1,  // Automatically generates a unique ID
    val table: TableVM = TableVM.placeholder(),
    val orders: List<OrderVM> = emptyList(),
    val paid:Boolean= false
){
    fun toVM(): ClientVM {
        //val id = if(this.id==-1) null else this.id
        return ClientVM(
            id = this.id,
            table = this.table,
            orders = this.orders,
            paid = this.paid
        )
    }
}