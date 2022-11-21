package com.example.queen.view.adapters

import com.example.queen.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.queen.data.model.entity.Order


class ListAdapter() : RecyclerView.Adapter<ListAdapter.ListViewHolder?>() {
    private var orderList = emptyList<Order>()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_procedure)
        val orderId: TextView = itemView.findViewById(R.id.order_id)
        val position: TextView = itemView.findViewById(R.id.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_order_row,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = orderList[position]
        holder.name.text = currentItem.name
        holder.orderId.text = currentItem.id.toString()
        holder.position.text = (position+1).toString()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
    fun  setData(orders: List<Order>){
        this.orderList = orders
        notifyDataSetChanged()
    }
}