package com.example.cabapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cabapp.CabApplication
import com.example.cabapp.data.entity.Authentication
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    private val appScope = CabApplication.appScope
    private val mutableUserLiveData: MutableLiveData<Authentication.User?> = MutableLiveData()
    val userLiveData: LiveData<Authentication.User?> = mutableUserLiveData

    fun getCurrentUser() {
        appScope.launch {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                mutableUserLiveData.postValue(Tasks.await(databaseReference.child("all_users").child(userId).get())
                    .getValue(Authentication.User::class.java))
            } else {
                mutableUserLiveData.postValue(null)
            }
        }
    }
}