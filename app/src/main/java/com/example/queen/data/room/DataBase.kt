package com.example.queen.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.queen.data.model.entity.Order
import com.example.queen.data.model.entity.Role
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.entity.UserRoles

@Database(
    entities = [
        User::class,
        Role::class,
        UserRoles::class,
        Order::class
               ], version = 2
    )
abstract class DataBase :RoomDatabase() {
    abstract fun getDaoUser(): DaoUser

    companion object{
        fun getDataBase(context: Context):DataBase{
            return Room.databaseBuilder(context.applicationContext,DataBase::class.java,"queen.db").fallbackToDestructiveMigration().build()
        }
    }
}