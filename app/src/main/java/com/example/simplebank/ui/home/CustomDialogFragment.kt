package com.example.simplebank.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.simplebank.R

class CustomDialogFragment:DialogFragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_dialog, container, false)
        return rootView
    }
    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}