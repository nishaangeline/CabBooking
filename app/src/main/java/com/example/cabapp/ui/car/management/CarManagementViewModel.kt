package com.example.cabapp.ui.car.management

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.cabapp.CabApplication
import com.example.cabapp.data.entity.Car
import com.example.cabapp.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CarManagementViewModel : ViewModel() {
    lateinit var database: DatabaseReference






    fun insertCar(vehicleNo: String, modelName: String,seatNo: String) {

        database = Firebase.database.reference
        val cars = Car(vehicleNo, modelName,seatNo)
        Log.e("Hi",database.toString())
//hi
        database.child("Car").child(vehicleNo).setValue(cars).addOnFailureListener{

            Toast.makeText(CabApplication.appContext, "Failed", Toast.LENGTH_SHORT).show()

        }.addOnSuccessListener {
            Toast.makeText(CabApplication.appContext, "Success", Toast.LENGTH_SHORT).show()
        }






    }

}