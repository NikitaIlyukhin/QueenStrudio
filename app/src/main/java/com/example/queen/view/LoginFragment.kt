package com.example.queen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.queen.MainActivity
import com.example.queen.R
import com.example.queen.databinding.LoginFragmentBinding
import com.example.queen.viewModel.UserViewModel

class LoginFragment:Fragment(R.layout.login_fragment) {
    private lateinit var binding : LoginFragmentBinding
    private val userViewModel by activityViewModels<UserViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null) {
            userViewModel.insertDefaultRole()
            // Войти-->
            binding.loginBtn.setOnClickListener {
                val phone = binding.editTextPhone.text.toString()
                val password = binding.editTextPassword.text.toString()
                userViewModel.getUserByPhone(phone)!!.observe((activity as MainActivity?)!!, Observer {
                    if(it==null)
                        binding.phoneLayout.error = getString(R.string.error_login)
                    else {
                        if(it.password!=password)
                            binding.passwordLayout.error = getString(R.string.error_password)
                        else {
                            userViewModel.setActiveUser(it)
                            userViewModel.goToNextFragment(this,FragmentUserCard.newInstance())
                        }
                    }
                })
            }
            binding.editTextPhone.addTextChangedListener { binding.phoneLayout.error = null }
            binding.editTextPassword.addTextChangedListener { binding.passwordLayout.error = null }
            // <--Войти

            // Забыл пароль-->
            binding.foggotPasswordBtn.setOnClickListener {
                val toast = Toast.makeText(this.requireContext(),"В разработке. Прошу понять и простить. ",Toast.LENGTH_LONG)
                toast.setGravity(0,0,0)
                toast.show()
            }
            // <--Забыл пароль
            // Регистрация-->
            binding.registrationBtn.setOnClickListener {
                userViewModel.goToNextFragment(this,CreateUserFragment.newInstance())
            }
            // <--Регистрация
        }
    }
}