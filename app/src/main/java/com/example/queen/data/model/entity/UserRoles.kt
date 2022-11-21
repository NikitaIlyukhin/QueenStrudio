package com.example.queen.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(primaryKeys = ["id","role_name"])
data class UserRoles(
    val id:Long,
    val role_name:String
)