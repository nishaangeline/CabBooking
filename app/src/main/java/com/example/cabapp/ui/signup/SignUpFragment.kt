package com.example.cabapp.ui.signup

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cabapp.R
import com.example.cabapp.databinding.FragmentSignupBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        observeLiveData()
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        val roles = resources.getStringArray(R.array.roles)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, roles)
        with(binding) {
            dropDownRole.showSoftInputOnFocus = false
            dropDownRole.inputType = InputType.TYPE_NULL
            dropDownRole.setAdapter(arrayAdapter)
            buttonSignUp.setOnClickListener {
                if (!(editTextEmail.text.isNullOrEmpty() && editTextPassword.text.isNullOrEmpty())) {
                    signUpViewModel.signUpUser(
                        binding.editTextEmail.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                }
            }
            buttonLogin.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    private fun observeLiveData() {
        signUpViewModel.authenticationLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it == SignUpViewModel.AuthenticationStatus.SUCCESS) {
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