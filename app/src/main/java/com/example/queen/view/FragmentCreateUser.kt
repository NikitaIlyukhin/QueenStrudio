package com.example.queen.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.queen.R
import com.example.queen.data.model.User
import com.example.queen.databinding.FragmentCreateUserBinding
import com.example.queen.viewModel.UserViewModel

class FragmentCreateUser:Fragment(R.layout.fragment_create_user) {
    private lateinit var binding:FragmentCreateUserBinding
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var user:User

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentCreateUser().apply {}
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null){
            binding.CreateUserBTN.setOnClickListener {
                user = getUser()
                if(checkUserData(user)) {
                    userViewModel.createUser(getUser())
                    userViewModel.goToNextFragment(this, FragmentLogin.newInstance())
                }
            }
            binding.errorPhoneTxt.addTextChangedListener {
                binding.errorPhoneTxt.visibility = View.INVISIBLE
            }
            binding.editTextTextPassword.addTextChangedListener {
                binding.errorPasswordTxt.visibility = View.INVISIBLE
            }
            binding.editTextFirstName.addTextChangedListener {
                binding.errorFirstNameTxt.visibility = View.INVISIBLE
            }
            binding.editTextLastName.addTextChangedListener {
                binding.errorLastNameTxt.visibility = View.INVISIBLE
            }
            binding.editTextMiddleName.addTextChangedListener {
                binding.errorMiddleNameTxt.visibility = View.INVISIBLE
            }
        }
    }
    private fun getUser():User{
        var phone = binding.editTextPhone2.text.toString()
        var password = binding.editTextTextPassword.text.toString()
        var firstName = binding.editTextFirstName.text.toString()
        var lastName = binding.editTextLastName.text.toString()
        var middleName = binding.editTextMiddleName.text.toString()

        this.user =
            User(phone,
                password,
                firstName,
                lastName,
                middleName)
        return user
    }
    private fun checkUserData(user:User):Boolean{
        var result:Boolean = true
        result = checkFirstName(user.firstName)
        result = checkLastName(user.lastName)
        result = checkPhone(user.phone)
        result = checkPassword(user.password)
        println("checkUserData $result")
        return result
    }
    private fun checkFirstName(firstName:String):Boolean{
        var result:Boolean = true
        when(firstName){
            "" -> {
                binding.errorFirstNameTxt.visibility = View.VISIBLE
//                binding.errorFirstNameTxt.setText("Тут надо что-то вписать")
                result = false
            }
//            "Ксения","Ксюша" -> {
//                binding.errorFirstNameTxt.setText("Привет, меня тоже зовут Ксения!")
//                binding.errorFirstNameTxt.setTextColor(Color.GREEN)
//            }
        }
        println("checkFirstName $result")
        return result
    }
    private fun checkLastName(lastName:String):Boolean{
        var result:Boolean = true
        when(lastName){
            "" -> {
                binding.errorLastNameTxt.visibility = View.VISIBLE
//                binding.errorLastNameTxt.setText("Тут надо что-то вписать")
                result = false
            }
        }
        println("checkLastName $result")
        return result
    }
    private fun checkPhone(phone:String):Boolean{
        var result:Boolean = true
        when(phone.length){
            0 -> {
                binding.errorPhoneTxt.visibility = View.VISIBLE
//                binding.errorPhoneTxt.setText("Тут надо что-то вписать")
                result = false
            }
            in 1..10 -> {
                binding.errorPhoneTxt.visibility = View.VISIBLE
//                binding.errorPhoneTxt.setText("Попробуй с 8-кой")
                result = false
            }
            in 12..Int.MAX_VALUE->{
                binding.errorPhoneTxt.visibility = View.VISIBLE
//                binding.errorPhoneTxt.setText("Здесь много цифр ")
                result = false
            }
        }
        return result
    }
    private fun checkPassword(password:String):Boolean{
        var result:Boolean = true
        when(password.length){
            0 -> {
                binding.errorPasswordTxt.visibility = View.VISIBLE
//                binding.errorPhoneTxt.setText("Тут надо что-то вписать")
                result = false
            }
            in 1..2 -> {
                binding.errorPasswordTxt.visibility = View.VISIBLE
//                binding.errorPhoneTxt.setText("Минимум 3 символа")
                result = false
            }
        }
        return result
    }
    private fun setError(control:TextView, errorText:String){
        Thread{
            control.visibility = View.VISIBLE
            control.setText(errorText)
        }.start()
    }
}