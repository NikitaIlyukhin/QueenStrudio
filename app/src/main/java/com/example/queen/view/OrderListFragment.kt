package com.example.queen.view

import androidx.fragment.app.Fragment
import com.example.queen.R

class OrderListFragment:Fragment(R.layout.list_order_row) {
    companion object{
        @JvmStatic
        fun newInstance() = OrderListFragment()
    }
}