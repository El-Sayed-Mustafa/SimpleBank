package com.example.simplebank.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebank.R
import com.example.simplebank.databinding.ListItemBinding
//import com.example.simplebank.databinding.ListItemBinding
import com.example.simplebank.db.User

class Adapter(private val users: List<User>):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater= LayoutInflater .from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(user: User){
        binding.name.text = user.name
        binding.accountNum.text = user.accountNum
        binding.balance.text = user.balance
    }
}