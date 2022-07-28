package com.example.cabapp.ui.bookingList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cabapp.R

class DriverSelectionFragment : Fragment() {

    companion object {
        fun newInstance() = DriverSelectionFragment()
    }

    private lateinit var viewModel: DriverSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_driver_selection, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriverSelectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}