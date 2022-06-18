package com.example.cabapp.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R
import com.example.cabapp.databinding.FragmentAdminBinding
import com.example.cabapp.ui.BaseFragment

class AdminFragment : BaseFragment() {

    private var _binding: FragmentAdminBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adminViewModel = ViewModelProvider(this)[AdminViewModel::class.java]
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        binding.buttonCarManagement.setOnClickListener {
            findNavController().navigate(R.id.fragment_car_management)
        }
        binding.buttonSignOut.setOnClickListener {
            adminViewModel.signOut()
            requireActivity().intent?.let { intent ->
                requireActivity().finish()
                startActivity(intent)
            }
        }
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        requireActivity().finish()
        return super.onBackPressed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}