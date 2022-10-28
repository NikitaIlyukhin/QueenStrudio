package com.example.queen.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.queen.MainActivity
import com.example.queen.R
import com.example.queen.databinding.FragmentLoginBinding
import com.example.queen.data.model.User
import com.example.queen.viewModel.UserViewModel


class FragmentLogin:Fragment(R.layout.fragment_login) {

    private lateinit var binding : FragmentLoginBinding
//    private val userViewModel by viewModels<UserViewModel>()
    private val userViewModel by activityViewModels<UserViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentLogin().apply {}
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null) {
            binding.ButtonRegistration.setOnClickListener {
                userViewModel.goToNextFragment(this,FragmentCreateUser.newInstance())
            }
            binding.buttonLogin.setOnClickListener {
                try{
                    userViewModel.getUserByPhone(getPhone())!!.observe((activity as MainActivity?)!!, Observer {
                        if(it==null) binding.errorLoginTxt.visibility = View.VISIBLE
                        else {
                            if(it.password!=getPassword()) binding.errorPasswordTxt.visibility = View.VISIBLE
                            else {
                                var user1: User = it
                                println(user1.firstName)
                                userViewModel.setActiveUser(it)
                                userViewModel.goToNextFragment(this,FragmentUserCard.newInstance())
                            }
                        }
                    })
                }
                catch (e:NullPointerException){
                    binding.errorLoginTxt.visibility = View.VISIBLE
                }
            }
            binding.editTextPhone.addTextChangedListener { binding.errorLoginTxt.visibility = View.INVISIBLE }
            binding.editTextPassword.addTextChangedListener { binding.errorPasswordTxt.visibility = View.INVISIBLE }
        }
    }

    fun getPhone():String{
        return binding.editTextPhone.text.toString()
    }
    fun getPassword():String{
        return binding.editTextPassword.text.toString()
    }

}