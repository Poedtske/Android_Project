package com.example.android_project.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.android_project.classes.DrinkCategory
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity(tableName = "Tables")
data class TableItem(
    @PrimaryKey(autoGenerate = true)val tableId: UUID?=null,
    val open:Boolean
)

