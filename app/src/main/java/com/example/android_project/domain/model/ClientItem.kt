package com.example.android_project.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.android_project.classes.DrinkCategory
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity(tableName = "Clients")
data class ClientItem(
    @PrimaryKey(autoGenerate = true)val clientId: Int?=null,
    val associatedTableId: Int,
)


data class TableAndClients(
    @Embedded val table: TableItem,
    @Relation(
        parentColumn = "tableId",
        entityColumn = "associatedTableId"
    )
    val clients:List<ClientItem>
)