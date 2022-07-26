package com.example.cabapp.ui.booking

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R
import com.example.cabapp.data.entity.Car
import com.example.cabapp.databinding.FragmentBookingBinding

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookingViewModel: BookingViewModel
    private var carsAdapter: CarsAdapter? = null
    private var selectedCar: Car? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bookingViewModel = ViewModelProvider(this)[BookingViewModel::class.java]
        bookingViewModel.fetchCar()
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        binding.dropDownRole.setOnClickListener {
            Log.e("Dropdown", binding.dropDownRole.text.toString())
            binding.dropDownRole.showDropDown()
        }
        binding.dropDownRole.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedCar = carsAdapter?.getItem(position)
                binding.dropDownRole.dismissDropDown()
            }

        bookingViewModel.carLiveData.observe(viewLifecycleOwner) { carsList ->
            if (carsAdapter == null) {
                carsAdapter =
                    CarsAdapter(requireContext(), R.layout.dropdown_item, carsList).apply {
                        setNotifyOnChange(true)
                    }
//                carsAdapter.setNotifyOnChange(true)
                binding.dropDownRole.setAdapter(carsAdapter)
            } else {
                carsAdapter?.updateList(carsList)
            }
        }
        with(binding) {
            dropDownRole.showSoftInputOnFocus = false
            dropDownRole.inputType = InputType.TYPE_NULL
//            dropDownRole.setAdapter(arrayAdapter)
            binding.buttonNext.setOnClickListener {
                if (selectedCar != null) {
                    findNavController().navigate(
                        BookingFragmentDirections.openBookingDetailsPage(
                            selectedCar!!
                        )
                    )
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        carsAdapter = null
    }
}