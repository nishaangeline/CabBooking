package com.example.cabapp.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cabapp.CabApplication
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {


    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mutableAuthenticationLiveData: MutableLiveData<AuthenticationStatus?> = MutableLiveData(null)
    val authenticationLiveData: LiveData<AuthenticationStatus?> = mutableAuthenticationLiveData

    fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener {
            mutableAuthenticationLiveData.value = AuthenticationStatus.FAILED
            Toast.makeText(CabApplication.appContext, "Failed", Toast.LENGTH_SHORT).show()
            Log.e("FireException", it.message.toString())
        }.addOnSuccessListener {
            mutableAuthenticationLiveData.value = AuthenticationStatus.SUCCESS
            Toast.makeText(CabApplication.appContext, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    enum class AuthenticationStatus {
        SUCCESS,
        FAILED
    }
}