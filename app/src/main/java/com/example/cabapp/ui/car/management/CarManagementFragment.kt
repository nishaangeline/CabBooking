package com.example.cabapp.ui.car.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R
import com.example.cabapp.databinding.FragmentCarManagementBinding
import com.example.cabapp.databinding.FragmentSignupBinding
import com.example.cabapp.ui.signup.SignUpViewModel


class CarManagementFragment : Fragment() {

    private var _binding: FragmentCarManagementBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var carManagementViewModel: CarManagementViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         carManagementViewModel =
            ViewModelProvider(this)[CarManagementViewModel::class.java]

        _binding = FragmentCarManagementBinding.inflate(inflater, container, false)
        with(binding) {
        binding.buttonAdd.setOnClickListener {
            carManagementViewModel.insertCar(
                editVehicleNo.text.toString().uppercase(),
                editModelName.text.toString(),
                editSeatNo.text.toString()
            )
        }
        return binding.root

    }}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}