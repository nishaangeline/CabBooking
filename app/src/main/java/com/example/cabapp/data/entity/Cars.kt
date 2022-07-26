package com.example.cabapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    @SerializedName("vehicle_name") val vehicleNo: String? = null,
    @SerializedName("model_name") val modelName: String? = null,
    @SerializedName("seat_no") val seatNo: String? = null
) : Parcelable