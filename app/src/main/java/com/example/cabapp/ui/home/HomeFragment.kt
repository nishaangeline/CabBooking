package com.example.cabapp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cabapp.data.entity.Authentication
import com.example.cabapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        observeLiveData()
        viewModel.getCurrentUser()
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun observeLiveData() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { currentUser ->
            val direction = when (currentUser?.role) {
                Authentication.UserType.ADMIN.ordinal -> {
                    HomeFragmentDirections.actionHomeToAdmin()
                }
                Authentication.UserType.ADMIN.ordinal -> {
                    HomeFragmentDirections.actionHomeToAdmin()
                }
                else -> {
                    HomeFragmentDirections.actionHomeToLogin()
                }
            }
            findNavController().navigate(direction)
        }
    }
}