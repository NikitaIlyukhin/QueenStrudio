package com.example.queen.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.queen.data.model.entity.Order
import com.example.queen.data.model.entity.User

data class UserOrders(
    @Embedded val user:User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val orders: List<Order>
)