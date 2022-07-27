package com.example.cabapp.data.entity

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingDetail(
    @get:PropertyName("departure_date") @set:PropertyName("departure_date") var departureDate: String? = null,
    @get:PropertyName("arrival_date") @set:PropertyName("arrival_date") var arrivalDate: String? = null,
    @get:PropertyName("from") @set:PropertyName("from")  var from: String? = null,
    @get:PropertyName("to") @set:PropertyName("to") var to: String? = null,
    @get:PropertyName("boarding_point") @set:PropertyName("boarding_point") var boardingPoint: String? = null,
    @get:PropertyName("vehicle_no") @set:PropertyName("vehicle_no") var vehicleNo: String? = null
) : Parcelable {

    enum class Availability(val value: String) {
        Available("available"),
        Engaged("Engaged"),
        Unavailable("unavailable")
    }
}