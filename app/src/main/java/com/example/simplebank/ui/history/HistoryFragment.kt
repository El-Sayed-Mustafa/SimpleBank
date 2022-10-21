package com.example.simplebank.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebank.R
import com.example.simplebank.databinding.FragmentHistoryBinding
import com.example.simplebank.ui.history.db.TransactionDatabase
import com.example.simplebank.ui.history.db.TransactionRepo


class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dao = TransactionDatabase.getInstance(application).transactionDao
        val repo = TransactionRepo(dao)
        val factory = HistoryViewModelFactory(repo)

        historyViewModel= ViewModelProvider(this, factory)[HistoryViewModel::class.java]


        binding.viewModel = historyViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        return binding.root
    }


    private fun initRecyclerView(){
        binding.recyclerView2.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }
    private fun displayUsersList() {
        val adapter = Adapter()
        binding.recyclerView2.adapter= adapter
        historyViewModel.transactions.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }


}