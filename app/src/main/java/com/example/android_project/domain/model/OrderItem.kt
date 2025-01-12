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
    @PrimaryKey(autoGenerate = true)val orderId: UUID?=null,
    val associatedClientId: UUID,
)

//1:M relation with client:orders
data class ClientWithOrders(
    @Embedded val client: ClientItem,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "associatedClientId"
    )
    val orders: List<OrderItem>
)

//M:M relation orders:food
@Entity(primaryKeys = ["orderId","foodId"])
data class OrderFoodCrossRef(
    val orderId: UUID,
    val foodId: Int
)

data class OrderWithFood(
    @Embedded val order:OrderItem,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "foodId",
        associateBy = Junction(OrderFoodCrossRef::class)
    )
    val food:List<FoodItem>
)

data class FoodWithOrders(
    @Embedded val food:FoodItem,
    @Relation(
        parentColumn = "foodId",
        entityColumn = "orderId",
        associateBy = Junction(OrderFoodCrossRef::class)
    )
    val orders:List<OrderItem>
)

//M:M relation orders:drinks
@Entity(primaryKeys = ["orderId","drinkId"])
data class OrderDrinksCrossRef(
    val orderId: UUID,
    val drinkId: UUID
)

data class OrderWithDrinks(
    @Embedded val order:OrderItem,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "drinkId",
        associateBy = Junction(OrderDrinksCrossRef::class)
    )
    val drinks:List<DrinkItem>
)

data class DrinksWithOrder(
    @Embedded val drink:DrinkItem,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "orderId",
        associateBy = Junction(OrderDrinksCrossRef::class)
    )
    val orders:List<OrderItem>
)