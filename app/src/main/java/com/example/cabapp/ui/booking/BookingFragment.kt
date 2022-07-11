package com.example.cabapp.ui.booking

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R

import com.example.cabapp.databinding.FragmentBookingBinding
import com.example.cabapp.ui.admin.AdminViewModel

class BookingFragment : Fragment() {

    companion object {
        fun newInstance() = BookingFragment()
    }
    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookingViewModel: BookingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.fragment_booking_detail);
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookingViewModel = ViewModelProvider(this).get(BookingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}