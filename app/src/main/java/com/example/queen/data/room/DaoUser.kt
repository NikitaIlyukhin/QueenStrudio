package com.example.queen.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.queen.data.model.entity.*
import com.example.queen.data.model.relations.RoleWithUsers
import com.example.queen.data.model.entity.UserRoles
import com.example.queen.data.model.relations.UserOrders
import com.example.queen.data.model.relations.UserWithRoles
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRole(role: Role)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserRoles(crossinline: UserRoles)

    @Query("SELECT * FROM user")
    fun getAllUsers():Flow<List<User>>

    @Query("SELECT * FROM user u WHERE u.phone=:phone")
    fun getUserByPhone(phone:String):LiveData<User>

    @Query("SELECT * FROM user u WHERE u.id=:id")
    fun getUserById(id:Long): User

    @Query("SELECT * FROM role r WHERE r.role_name=:roleName")
    fun getUsersOfRole(roleName:String):Flow<List<RoleWithUsers>>

    @Query("SELECT * FROM user u WHERE u.id=:userId")
    fun getUserRoles(userId:Long):Flow<List<UserWithRoles>>

    @Query("SELECT * FROM user u WHERE u.id=:userId")
    fun getUserOrders(userId:Long):LiveData<List<UserOrders>>

    @Query("SELECT * FROM `order` o WHERE o.user_id =:userId")
    fun getOrdersByUserId(userId:Long):Flow<List<Order>>

}