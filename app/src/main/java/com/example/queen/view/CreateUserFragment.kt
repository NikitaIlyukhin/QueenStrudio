package com.example.queen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.queen.R
import com.example.queen.data.model.entity.User
import com.example.queen.databinding.CreateUserFragmentBinding
import com.example.queen.viewModel.UserViewModel

class CreateUserFragment:Fragment(R.layout.create_user_fragment){
    private lateinit var binding: CreateUserFragmentBinding
    private val userViewModel by activityViewModels<UserViewModel>()
    private lateinit var user: User

    companion object{
        @JvmStatic
        fun newInstance() = CreateUserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateUserFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState==null){
            // Обнуление ошибок при вводе -->
            binding.editTextFirstName.addTextChangedListener { binding.firstNameLayout.error = null }
            binding.editTextLastName.addTextChangedListener { binding.lastNameLayout.error = null }
            binding.editTextMiddleName.addTextChangedListener { binding.middleNameLayout.error = null }
            binding.editTextPhone.addTextChangedListener { binding.phoneLayout.error = null }
            binding.editTextPassword.addTextChangedListener { binding.passwordLayout.error = null }
            // <-- Обнуление ошибок при вводе
            // Зарегистрироваться-->
            binding.loginBtn.setOnClickListener {
                user = getUser()
                if(checkUserData(user)) {
                    userViewModel.createUser(user)
                    userViewModel.setActiveUser(user)
                    userViewModel.goToNextFragment(this, FragmentUserCard.newInstance())
                }
            }
            // <--Зарегистрироваться
        }
    }
    private fun getUser(): User {
        val phone = binding.editTextPhone.text.toString()
        val password = binding.editTextPassword.text.toString()
        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val middleName = binding.editTextMiddleName.text.toString()

        this.user =
            User(phone,
                password,
                firstName,
                lastName,
                middleName)
        return user
    }
    private fun checkUserData(user: User):Boolean{
        var result = true
        //result = checkFirstName(user.firstName)
        //result = checkLastName(user.lastName)
        //result = checkPhone(user.phone)
        //result = checkPassword(user.password)
        return result
    }
    private fun checkFirstName(firstName:String):Boolean{
        var result = true
        when(firstName){
            "" -> {
                binding.firstNameLayout.error = "Тут надо что-то вписать"
                result = false
            }
        }
        return result
    }
    private fun checkLastName(lastName:String):Boolean{
        var result = true
        when(lastName){
            "" -> {
                binding.lastNameLayout.error = "Тут надо что-то вписать"
                result = false
            }
        }
        return result
    }
    private fun checkPhone(phone:String):Boolean{
        var result = true
        when(phone.length){
            0 -> {
                binding.phoneLayout.error = "Тут надо что-то вписать"
                result = false
            }
            in 1..10 -> {
                binding.phoneLayout.error = "Попробуй с 8-кой"
                result = false
            }
            in 12..Int.MAX_VALUE->{
                binding.phoneLayout.error = "Здесь много цифр"
                result = false
            }
        }
        return result
    }
    private fun checkPassword(password:String):Boolean{
        var result = true
        when(password.length){
            0 -> {
                binding.passwordLayout.error = "Тут надо что-то вписать"
                result = false
            }
            in 1..2 -> {
                binding.passwordLayout.error = "Минимум 3 символа"
                result = false
            }
        }
        return result
    }
}