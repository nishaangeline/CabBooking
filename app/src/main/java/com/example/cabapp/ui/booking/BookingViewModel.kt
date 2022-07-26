package com.example.cabapp.ui.booking

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*


class BookingViewModel : ViewModel() {
    lateinit var database: DatabaseReference
    fun fetchCar() {

       database= FirebaseDatabase.getInstance().getReference("Car").child("vehicleNo")
     database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (sampleSnapshot in dataSnapshot.children) {
                    Log.d(
                        "Booking",
                        "onDataChange: sampleSnapshot " + sampleSnapshot.key + " = " + sampleSnapshot.value
                    )
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException() // don't ignore errors
            }
        })

    }




}