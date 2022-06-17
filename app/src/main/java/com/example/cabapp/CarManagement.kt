package com.example.cabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.cabapp.databinding.ActivityCarManagementBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference

class CarManagement : AppCompatActivity() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
  //  private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var firebaseDatabase:DatabaseReference
    private var activityCarManagementBinding: ActivityCarManagementBinding? = null
    private val binding get() = activityCarManagementBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCarManagementBinding = ActivityCarManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener{
            val vehicleNo = binding.editVehicleNo.text.toString()
            val modelName = binding.editModelName.text.toString()
            val seatNo = binding.editSeatNo.text.toString()
            firebaseDatabase=FirebaseDatabase.getInstance().getReference("Cars")
            val carValues=Cars(vehicleNo,modelName,seatNo)
            firebaseDatabase.child(vehicleNo).setValue(carValues).addOnSuccessListener {
              binding.editModelName.text?.clear()
                binding.editVehicleNo.text?.clear()
                binding.editSeatNo.text?.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
                       }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}