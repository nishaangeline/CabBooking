package com.example.cabapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cabapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var activityMainBinding: ActivityMainBinding? = null
    private val binding get() = activityMainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val roles = resources.getStringArray(R.array.roles)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, roles)
        binding.dropDownRole.showSoftInputOnFocus = false
        binding.dropDownRole.inputType = InputType.TYPE_NULL
        binding.dropDownRole.setAdapter(arrayAdapter)


        binding.buttonSignUp.setOnClickListener {
            signUpUser()
        }
        binding.buttonLogin.setOnClickListener {
            loginUser()
        }
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.dropDownRole.showDropDown()
        }, 3000)
    }

    private fun signUpUser() {
        firebaseAuth.createUserWithEmailAndPassword(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        ).addOnFailureListener {
            Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
            Log.e("FireException", it.message.toString())
        }.addOnCompleteListener {
            Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser() {
        firebaseAuth.signInWithEmailAndPassword(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        ).addOnFailureListener {
            Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
            Log.e("FireException", it.message.toString())
        }.addOnSuccessListener {
            Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}