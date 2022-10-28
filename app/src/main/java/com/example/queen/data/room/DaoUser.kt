package com.example.queen.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.queen.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User)

    @Query("SELECT * FROM user")
    fun getAllUsers():Flow<List<User>>

    @Query("SELECT * FROM user u WHERE u.phone=:phone")
    fun getUserByPhone(phone:String):LiveData<User>

    @Query("SELECT * FROM user u WHERE u.id=:id")
    fun getUserById(id:Long):User
}