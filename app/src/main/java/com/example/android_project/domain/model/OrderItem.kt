package com.example.android_project.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Entity(tableName = "Orders")
data class OrderItem(
    @PrimaryKey(autoGenerate = true)val orderId: Int?=null,
    val associatedClientId: Int,
)