package com.example.simplebank.ui.Dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.simplebank.R
import com.example.simplebank.databinding.FragmentDialogBinding
import com.example.simplebank.db.User
import com.example.simplebank.db.UserRepo

class CustomDialogFragment(private val user: User) : DialogFragment(), OnItemSelectedListener {
    private lateinit var binding: FragmentDialogBinding

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
//        spinner.onItemSelectedListener = this

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.confirmButton.setOnClickListener {

            val fromUser = binding.fromUser.text.toString()

            binding.confirmButton.setOnClickListener {
                Toast.makeText(this.context,
                    "From $fromUser To ${user.toString()}",
                    Toast.LENGTH_SHORT).show()

            }

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

        binding.fromUser.text = user.name

        return binding.root
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        Toast.makeText(binding.root.context,
            "From ${user.name}To  ${parent?.getItemAtPosition(position).toString()}",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

