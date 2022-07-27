package com.example.cabapp.data.entity

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    @get:PropertyName("vehicle_name") @set:PropertyName("vehicle_name") var vehicleNo: String? = null,
    @get:PropertyName("model_name") @set:PropertyName("model_name") var modelName: String? = null,
    @get:PropertyName("seat_no") @set:PropertyName("seat_no")  var seatNo: String? = null,
    @get:PropertyName("booking_status") @set:PropertyName("booking_status") var bookingStatus: String? = "available"
) : Parcelable {

    enum class Availability(val value: String) {
        Available("available"),
        Engaged("Engaged"),
        Unavailable("unavailable")
    }
}