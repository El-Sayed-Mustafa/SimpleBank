package com.example.simplebank.ui.Dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.simplebank.R
import com.example.simplebank.databinding.FragmentDialogBinding
import com.example.simplebank.ui.history.HistoryFragment
import com.example.simplebank.ui.history.HistoryViewModel
import com.example.simplebank.ui.history.HistoryViewModelFactory
import com.example.simplebank.ui.history.db.Transaction
import com.example.simplebank.ui.history.db.TransactionDatabase
import com.example.simplebank.ui.history.db.TransactionRepo
import com.example.simplebank.ui.home.db.User
import com.example.simplebank.ui.home.db.UserDatabase
import com.example.simplebank.ui.home.db.UserRepo
import com.example.simplebank.ui.home.HomeViewModel
import com.example.simplebank.ui.home.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CustomDialogFragment(private val fromUser: User) : DialogFragment(), OnItemSelectedListener {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: FragmentDialogBinding
    private lateinit var homeViewModel: HomeViewModel

    var transaction = Transaction(0, "", "", "", "", "", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dialog,
            container,
            false
        )
        val spinner: Spinner = binding.spinnerUsers
        spinner.onItemSelectedListener = this

        val application = requireNotNull(this.activity).application

        val dao = UserDatabase.getInstance(application).userDao
        val repo = UserRepo(dao)
        val factory = UserViewModelFactory(repo)

        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.lifecycleOwner = this


        val applicationHistory = requireNotNull(this.activity).application

        val daoHistory = TransactionDatabase.getInstance(applicationHistory).transactionDao
        val repoHistory = TransactionRepo(daoHistory)
        val factoryHistory = HistoryViewModelFactory(repoHistory)

        historyViewModel = ViewModelProvider(this, factoryHistory)[HistoryViewModel::class.java]


        binding.cancelButton.setOnClickListener {
            dismiss()
        }





        binding.fromUser.text = fromUser.name

        binding.confirmButton.setOnClickListener {

        }
        ArrayAdapter.createFromResource(
            binding.root.context,
            R.array.users,
            R.layout.spinner_dropdown_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)

            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }



        return binding.root
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val receiver = parent?.getItemAtPosition(position).toString()
        getReceiver(receiver)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun getReceiver(receiver: String) {

        lifecycleScope.launch {
            val toUser = homeViewModel.getUser(receiver)

            withContext(Dispatchers.Main) {

            }

            binding.confirmButton.setOnClickListener {

                val balance = binding.amountTransfer.text.toString()
                if (balance.isEmpty()) {
                    Toast.makeText(requireContext(),
                        "Please Enter amount money",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                if (toUser.id == fromUser.id) {
                    Toast.makeText(requireContext(),
                        "You can't make transfer money for the same user",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                var Sender = fromUser.balance.toFloat()

                transaction.receiverBalance = toUser.balance
                transaction.senderBalance = fromUser.balance
                Sender -= balance.toFloat()
                var Receiver = toUser.balance.toFloat()
                Receiver += balance.toFloat()
                if (toUser.id != fromUser.id && balance.isNotEmpty()) {

                    if (Sender < 0) {
                        Toast.makeText(requireContext(),
                            "${fromUser.name} doesn't have enough money ",
                            Toast.LENGTH_SHORT)
                            .show()
                        getReceiver(receiver)
                    }
                    if(Sender>=0) {

                        fromUser.balance = Sender.toString()

                        toUser.balance = Receiver.toString()
                        homeViewModel.insertUser(toUser)
                        homeViewModel.insertUser(fromUser)
                        homeViewModel.users


                        val current = LocalDateTime.now()

                        val formatter = DateTimeFormatter.ofPattern("HH:mm\nyyyy-MM-dd")
                        val currentTime = current.format(formatter)
                        transaction.receiver = toUser.name
                        transaction.sender = fromUser.name
                        transaction.currentTime = currentTime
                        transaction.amount = balance

                        historyViewModel.insertTransaction(transaction)
                        dismiss()

                        findNavController().navigate(R.id.navigation_home)
                    }
                }
            }

        }


    }


}

