package com.example.cabapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R
import com.example.cabapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observeLiveData()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        with(binding) {
            buttonSignUp.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginToSignup())
            }
            buttonLogin.setOnClickListener {
                if (!(editTextEmail.text.isNullOrEmpty() && editTextPassword.text.isNullOrEmpty())) {
                    loginViewModel.loginUser(
                        editTextEmail.text.toString(),
                        editTextPassword.text.toString()
                    )
                }
            }
        }
        return binding.root
    }

    private fun observeLiveData() {
        loginViewModel.authenticationLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it == LoginViewModel.AuthenticationStatus.SUCCESS) {
                    findNavController().navigate(R.id.fragment_admin)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}