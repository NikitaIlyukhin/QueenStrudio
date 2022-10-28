package com.example.queen.viewModel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.queen.R
import com.example.queen.data.model.User
import com.example.queen.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserViewModel():ViewModel() {
    val liveDataUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    var listUsers: Flow<List<User>>? = null

    fun initializeDB(context: Context){
        UserRepository.initializeDB(context)
    }

    fun createUser(user:User) {
        UserRepository.createUser(user)
    }
    fun getUserById(id:Long):User{
        return UserRepository.getUser(id)
    }
    fun getUserByPhone(phone:String):LiveData<User>{
        return UserRepository.getUserByPhone(phone)
    }
    fun setActiveUser(user:User){
        liveDataUser.value=user

    }
    fun getActiveUser(): User {
        println(liveDataUser.value)
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

}