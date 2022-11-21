package com.example.queen.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "role")
data class Role (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "role_name")
    val roleName:String
)