package com.example.queen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.queen.R
import com.example.queen.databinding.FragmentUserCardBinding
import com.example.queen.viewModel.UserViewModel

class FragmentUserCard: Fragment(R.layout.fragment_user_card) {
    private val userViewModel by activityViewModels<UserViewModel>()
    private lateinit var binding: FragmentUserCardBinding
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentUserCard().apply {}
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentUserCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null)
        {
            userViewModel.liveDataUser.observe(activity as LifecycleOwner,{
                binding.userName.text="${it.firstName} ${it.lastName}"
                print("")
            })
        }
    }
}