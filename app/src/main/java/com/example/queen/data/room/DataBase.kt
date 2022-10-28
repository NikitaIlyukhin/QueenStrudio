package com.example.queen.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.queen.data.model.User
import com.example.queen.data.room.DaoUser

@Database(entities = [User::class], version = 1)
abstract class DataBase :RoomDatabase() {
    abstract fun getDaoUser(): DaoUser

    companion object{
        fun getDataBase(context: Context):DataBase{
            return Room.databaseBuilder(context.applicationContext,DataBase::class.java,"queen.db").build()
        }
    }
}