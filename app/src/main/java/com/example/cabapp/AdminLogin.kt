package com.example.cabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AdminLogin : AppCompatActivity() {
    lateinit var btnCar:Button
    lateinit var btnBooking:Button
    lateinit var btnDriver:Button
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        btnCar=findViewById(R.id.button3);
        btnBooking=findViewById(R.id.button4);
        btnDriver=findViewById(R.id.button5)
        btnCar.setOnClickListener{
            addCar()
        }
    }
    private fun addCar()
    {


    }
}