package com.example.simplebank.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplebank.R
import com.example.simplebank.databinding.FragmentHomeBinding
import com.example.simplebank.db.User
import com.example.simplebank.db.UserDatabase
import com.example.simplebank.db.UserRepo
import com.example.simplebank.ui.Dialog.CustomDialogFragment

class HomeFragment : Fragment() {


    private lateinit var homeViewModel:HomeViewModel
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )


        val application = requireNotNull(this.activity).application

        val dao = UserDatabase.getInstance(application).userDao
        val repo = UserRepo(dao)
        val factory = UserViewModelFactory(repo)

        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]


        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }
    private fun displayUsersList() {
        val adapter = Adapter { selectItem: User ->
            listItemClicked(selectItem)
        }
        binding.recyclerView.adapter= adapter
        homeViewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }


    private fun listItemClicked(user: User){

        CustomDialogFragment(user).show(childFragmentManager, CustomDialogFragment.TAG)

    }

}