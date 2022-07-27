package com.example.cabapp.ui.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cabapp.data.entity.Car
import com.google.firebase.database.*

class BookingViewModel : ViewModel() {
    var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("cars")

    private val mutableCarLiveData: MutableLiveData<List<Car>> = MutableLiveData()
    val carLiveData: LiveData<List<Car>> = mutableCarLiveData

    fun fetchCar() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mutableCarLiveData.value = dataSnapshot.children.toList().map {
                    it.getValue(Car::class.java) ?: Car()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}