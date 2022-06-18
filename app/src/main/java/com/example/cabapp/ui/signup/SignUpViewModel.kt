package com.example.cabapp.ui.signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cabapp.CabApplication
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {


    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mutableAuthenticationLiveData: MutableLiveData<AuthenticationStatus?> = MutableLiveData(null)
    val authenticationLiveData: LiveData<AuthenticationStatus?> = mutableAuthenticationLiveData

    fun signUpUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnFailureListener {
            mutableAuthenticationLiveData.value = AuthenticationStatus.FAILED
            Toast.makeText(CabApplication.appContext, "Failed", Toast.LENGTH_SHORT).show()
            Log.e("FireException", it.message.toString())
        }.addOnCompleteListener {
            mutableAuthenticationLiveData.value = AuthenticationStatus.SUCCESS
            Toast.makeText(CabApplication.appContext, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    enum class AuthenticationStatus {
        SUCCESS,
        FAILED
    }
}