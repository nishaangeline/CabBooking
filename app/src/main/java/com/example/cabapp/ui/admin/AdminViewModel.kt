package com.example.cabapp.ui.admin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AdminViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signOut() {
        firebaseAuth.signOut()
    }
}