package com.example.android_project.classes

import com.example.android_project.domain.model.ClientItem
import com.example.android_project.domain.model.DrinkItem
import java.util.UUID
import kotlin.random.Random

data class ClientVM(
    val id: Int = Random.nextInt(),  // Automatically generates a unique ID
    var tableId: Int,
){
    fun toEntity(): ClientItem {
        //val id = if(this.id==-1) null else this.id
        return ClientItem(
            clientId = id,
            associatedTableId = tableId,
        )
    }

    companion object {
        fun fromEntity(entity: ClientItem): ClientVM {
            return ClientVM(
                id = entity.clientId!!,
                tableId = entity.associatedTableId
            )
        }
    }
}