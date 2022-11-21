package com.example.queen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import com.example.queen.viewModel.UserViewModel
import com.example.queen.view.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var transaction: FragmentTransaction
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            userViewModel.initializeDB(applicationContext)
            transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container_view, LoginFragment.newInstance(),"loginFragment").commit()
        }
    }
}