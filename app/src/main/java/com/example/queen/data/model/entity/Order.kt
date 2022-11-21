package com.example.queen.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "order")
data class Order(
    @ColumnInfo(name = "name")
    val name:String,
//    @ColumnInfo(name = "create_data")
//    val createData: Date,
//    @ColumnInfo(name = "visit_data")
//    val visitData: Date,
//    @ColumnInfo(name = "version")
//    val version: Number,
    @ColumnInfo(name = "user_id")
    val userId: Long
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long? = null
}

