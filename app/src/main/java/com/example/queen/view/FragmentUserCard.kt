package com.example.queen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.queen.R
import com.example.queen.data.model.entity.Order
import com.example.queen.data.model.entity.User
import com.example.queen.data.model.relations.UserOrders
import com.example.queen.databinding.FragmentUserCardBinding
import com.example.queen.view.adapters.ListAdapter
import com.example.queen.viewModel.UserViewModel

class FragmentUserCard: Fragment(R.layout.fragment_user_card) {
    private val userViewModel by activityViewModels<UserViewModel>()
    private lateinit var binding: FragmentUserCardBinding
    private lateinit var activeUser: User
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:ListAdapter
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentUserCard().apply {}
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentUserCardBinding.inflate(inflater)
        //List Orders->
        adapter = ListAdapter()
        recyclerView = binding.ordersList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //<-List Orders
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null)        {
            userViewModel.liveDataUser.observe(activity as LifecycleOwner,{
                activeUser = it
                binding.userName.text="${activeUser.firstName} ${activeUser.lastName}"
            })

            if(activeUser!=null){
                userViewModel.getOrdersByUserId(activeUser.id!!).asLiveData().observe(activity as LifecycleOwner){
                    it.forEach { order -> adapter.setData(it) }
                }

                binding.makeOrder.setOnClickListener {
                    userViewModel.createOrder(Order("ноготочки", activeUser.id!!))
                }

            }
        }
    }
}