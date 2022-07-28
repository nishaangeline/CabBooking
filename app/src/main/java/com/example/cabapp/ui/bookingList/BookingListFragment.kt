package com.example.cabapp.ui.bookingList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cabapp.R

class BookingListFragment : Fragment() {

    companion object {
        fun newInstance() = BookingListFragment()
    }

    private lateinit var viewModel: BookingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_booking_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookingListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}