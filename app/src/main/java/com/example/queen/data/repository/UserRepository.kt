package com.example.queen.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.queen.data.model.entity.Order
import com.example.queen.data.model.entity.Role
import com.example.queen.data.room.DataBase
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.entity.UserRoles
import com.example.queen.data.model.relations.UserOrders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class UserRepository {

    companion object {
        var dataBase: DataBase? = null
        var user: User? = null
        var userModel: LiveData<User>? = MutableLiveData<User>()
        var userOrders:LiveData<List<UserOrders>> = MutableLiveData<List<UserOrders>>()
        var orders:LiveData<List<Order>> = MutableLiveData<List<Order>>()

        fun initializeDB(context: Context) {
            dataBase = DataBase.getDataBase(context)
        }

        fun getUser(id: Long): User {
            return dataBase!!.getDaoUser().getUserById(id)
        }
        fun getActiveUser(): User {
            return user!!
        }
        fun setActiveUser(id: Long){
          user = dataBase!!.getDaoUser().getUserById(id)
        }

        fun getUserByPhone(phone: String): LiveData<User> {
            userModel = dataBase!!.getDaoUser().getUserByPhone(phone)
            return userModel as LiveData<User>
        }

        fun createUser(user: User) {
            var id:Long
            CoroutineScope(Dispatchers.IO).launch {
                id = dataBase!!.getDaoUser().insertUser(user)
                dataBase!!.getDaoUser().insertUserRoles(UserRoles(id,"user"))
            }
        }
        fun getAllUsers(): Flow<List<User>> {
            return dataBase!!.getDaoUser().getAllUsers()
        }
        fun insertDefaultRole(){
            CoroutineScope(Dispatchers.IO).launch {
                dataBase!!.getDaoUser().insertRole(Role("user"))
                dataBase!!.getDaoUser().insertRole(Role("master"))
                dataBase!!.getDaoUser().insertRole(Role("admin"))
            }
        }
        fun createOrder(order: Order){
            CoroutineScope(Dispatchers.IO).launch {
                dataBase!!.getDaoUser().insertOrder(order)
            }
        }
        fun getUserOrder(userId:Long):LiveData<List<UserOrders>> {
            CoroutineScope(Dispatchers.IO).launch {
                userOrders = dataBase!!.getDaoUser().getUserOrders(userId)
            }
            return userOrders
        }
        fun getOrderByUserId(userId:Long):Flow<List<Order>> {
            return dataBase!!.getDaoUser().getOrdersByUserId(userId)
        }
    }
}