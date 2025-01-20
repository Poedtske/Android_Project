package com.example.android_project.data.model

import com.example.android_project.classes.ClientVM
import com.example.android_project.classes.OrderVM
import com.example.android_project.classes.TableVM

data class TableEntity(
    val id:Int=-1,
    val clients:Set<ClientVM> = emptySet()
) {
    fun toVM(): TableVM {
        //val id = if(this.id==-1) null else this.id
        return TableVM(
            id = this.id,
            clients = this.clients
        )
    }
}