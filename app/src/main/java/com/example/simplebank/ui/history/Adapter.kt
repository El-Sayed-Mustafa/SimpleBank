package com.example.simplebank.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebank.R
import com.example.simplebank.databinding.ListItemHistoryBinding
import com.example.simplebank.ui.history.db.Transaction

class Adapter : ListAdapter<Transaction, MyViewHolder>(UserDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemHistoryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_history, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }


}

class MyViewHolder(val binding: ListItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction) {
        binding.tvSender.text = transaction.sender
        binding.tvReceiver.text = transaction.receiver
        binding.tvTime.text = transaction.currentTime
        binding.tvAmountSender.text = "-${transaction.amount}"

        binding.tvAmount.text = transaction.amount
        binding.tvSenderBefore.text=transaction.senderBalance
        binding.tvReceiverBefore.text = transaction.receiverBalance
        val SenderBalanceAfter = transaction.senderBalance.toFloat()-transaction.amount.toFloat()
        val senderBalance = SenderBalanceAfter
        binding.tvSenderAfter.text = senderBalance.toString()


        binding.tvReceiverAmount.text = "+${transaction.amount}"

        val ReceiverBalanceAfter = transaction.receiverBalance.toFloat()+transaction.amount.toFloat()
        val receiverBalance = ReceiverBalanceAfter

        binding.tvReceiverAfter.text = receiverBalance.toString()


    }

}

class UserDiffCallBack : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem === oldItem
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == oldItem
    }
}
