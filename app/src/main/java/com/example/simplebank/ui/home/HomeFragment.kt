package com.example.simplebank.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simplebank.R
import com.example.simplebank.databinding.FragmentHomeBinding
import com.example.simplebank.db.UserDatabase
import com.example.simplebank.db.UserRepo
import com.example.simplebank.ui.notifications.NotificationsViewModel

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
        displayUsersList()
        return binding.root
    }

    private fun displayUsersList() {
        homeViewModel.users.observe(viewLifecycleOwner, Observer {
            Log.i("MyTag", it.toString())
        })
    }

}