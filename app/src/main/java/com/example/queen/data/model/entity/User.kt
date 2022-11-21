package com.example.queen.data.model.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "phone")
    var phone:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "first_name")
    var firstName:String,
    @ColumnInfo(name = "last_name")
    var lastName:String,
    @ColumnInfo(name = "middle_name")
    var middleName:String,
//    @ColumnInfo(name = "birth_day")
//    var birthDate:Date
    ){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long? = null
}