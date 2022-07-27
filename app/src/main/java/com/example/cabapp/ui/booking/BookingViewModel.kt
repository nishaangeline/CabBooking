package com.example.cabapp.ui.booking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cabapp.data.entity.Car
import com.google.firebase.database.*
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken


class BookingViewModel : ViewModel() {
    lateinit var database: DatabaseReference

    private val mutableCarLiveData: MutableLiveData<List<Car>> = MutableLiveData()
    val carLiveData: LiveData<List<Car>> = mutableCarLiveData


    fun fetchCar() {

        database = FirebaseDatabase.getInstance().getReference("cars")
        //Log.e("Status",status.toString())
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                 //Bhahi entha code poten ..I got the value of status but the IF doesnt work!!
                    for (sampleSnapshot in dataSnapshot.children.toList()) {
                        val status = sampleSnapshot.child("bookingStatus").value
//                var carHash = dataSnapshot.value as HashMap<String, Any>
//                carHash.va
                        val statusValue = status.toString()
                        Log.d("booking status", status.toString())
                        if (statusValue.equals("Available")) {
                            mutableCarLiveData.value = dataSnapshot.children.toList().map {
                                it.getValue(Car::class.java) ?: Car()
                            }
                        }//Gson().fromJson( .toString(), object : TypeToken<List<Car>>() {}.type)
//                for (sampleSnapshot in dataSnapshot.children.toList()) {
//                    Log.d(
//                        "Booking",
//                        "onDataChange: sampleSnapshot " + sampleSnapshot.key + " = " + sampleSnapshot.value
//                    )
//                }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    throw databaseError.toException() // don't ignore errors
                }
            })
    }

}