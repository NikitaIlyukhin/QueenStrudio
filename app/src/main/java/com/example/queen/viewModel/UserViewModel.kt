package com.example.queen.viewModel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.queen.R
import com.example.queen.data.model.entity.Order
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.relations.UserOrders
import com.example.queen.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserViewModel():ViewModel() {
    val liveDataUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    var listUsers: Flow<List<User>>? = null
    var liveDataUserOrders: LiveData<List<Order>>? = null

    fun initializeDB(context: Context){
        UserRepository.initializeDB(context)
    }

    fun createUser(user: User) {
        UserRepository.createUser(user)
    }
    fun getUserById(id:Long): User {
        return UserRepository.getUser(id)
    }
    fun getUserByPhone(phone:String):LiveData<User>{
        return UserRepository.getUserByPhone(phone)
    }
    fun setActiveUser(user: User){
        liveDataUser.value=user
        println(user.phone)
//        var activeUserModel = UserRepository.getUserByPhone(user.phone)
//        println(activeUserModel.value!!.id)
//        UserRepository.setActiveUser(activeUserModel.value.id)
    }
    fun getActiveUser(): User {
        return liveDataUser.value!!
    }
    fun getAllUsers(): Flow<List<User>> {
        listUsers = UserRepository.getAllUsers()
        return listUsers as Flow<List<User>>
    }
    fun goToNextFragment(fragment: Fragment, nextFragment: Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view,nextFragment)
            .commit()
    }
    fun insertDefaultRole(){
        UserRepository.insertDefaultRole()
    }
    fun createOrder(order: Order){
        UserRepository.createOrder(order)
    }
    fun getOrdersByUserId(userId:Long):Flow<List<Order>>{
        return UserRepository.getOrderByUserId(userId)
    }
    fun getUserOrders(userId:Long):LiveData<List<UserOrders>>{
        return UserRepository.getUserOrder(userId)
    }

}