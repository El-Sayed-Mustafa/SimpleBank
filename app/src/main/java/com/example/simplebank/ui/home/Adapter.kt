package com.example.simplebank.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebank.R
import com.example.simplebank.databinding.ListItemBinding
//import com.example.simplebank.databinding.ListItemBinding
import com.example.simplebank.db.User

class Adapter(private val clickListener: (User)->Unit) : ListAdapter<User, MyViewHolder>(UserDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }


}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User,clickListener: (User) -> Unit) {
        binding.name.text = user.name
        binding.accountNum.text = user.accountNum
        binding.balance.text = user.balance
        binding.root.setOnClickListener {
//            showDialog("Transaction","Save",
//                null,"cancel",null)

            clickListener(user)
        }
    }

    fun showDialog(
        message:String?=null,
        posButton:String?=null,
        posButtonAction:DialogInterface.OnClickListener?=null,
        negButtonName:String?=null,
        negButtonAction: DialogInterface.OnClickListener?=null
    ){
        val dialog = AlertDialog.Builder(binding.root.context)
            .setMessage(message)
            .setPositiveButton(posButton,posButtonAction)
            .setNegativeButton(negButtonName,negButtonAction)
        dialog.show()
    }
}

class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem === oldItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == oldItem
    }
}
