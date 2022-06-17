package com.example.cabapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.cabapp.databinding.ActivityAdminLoginBinding
import com.example.cabapp.databinding.ActivityMainBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdminLogin : AppCompatActivity() {
        private var activityAdminLoginBinding: ActivityAdminLoginBinding? = null
        private val binding get() = activityAdminLoginBinding!!

    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminLoginBinding= ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener{
            val intent = Intent(this,CarManagement::class.java)
            startActivity(intent)
    }

}}