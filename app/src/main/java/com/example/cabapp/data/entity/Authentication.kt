package com.example.cabapp.data.entity

class Authentication {
    data class User(
        val email: String = "",
        val role: Int = -1
    )
    enum class UserType {
        ADMIN,
        MANAGER,
        USER,
        DRIVER
    }
}