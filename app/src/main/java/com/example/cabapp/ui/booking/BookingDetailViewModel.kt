package com.example.cabapp.ui.booking

import androidx.lifecycle.ViewModel
import com.example.cabapp.data.entity.BookingDetail
import com.google.firebase.database.*
import java.util.*

class BookingDetailViewModel : ViewModel() {
    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("bookings")

//    private val mutableCarLiveData: MutableLiveData<List<Boo>> = MutableLiveData()
//    val carLiveData: LiveData<List<Car>> = mutableCarLiveData

    val existingBookingIds = arrayListOf<String>()

    init {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                existingBookingIds.clear()
                existingBookingIds.addAll(dataSnapshot.children.toList().map {
                    it.key ?: ""
                })
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun bookCar(bookingDetail: BookingDetail) {
        var bookingId: String
        do {
            bookingId = UUID.randomUUID().toString()
        } while (!existingBookingIds.contains(bookingId))
        databaseReference.child(bookingId).setValue(bookingDetail)
    }
}