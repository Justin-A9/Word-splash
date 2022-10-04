package com.example.hiiii.datasource

data class Users (
    val email: String="",
    val password: String="",
    val confirmPassword: String="",
    val firstName: String="",
    val id: String ="",
    val lastName: String="",
    val image: String="",
    val mobile: Long=0,
    val gender: String="",
    val profileCompleted: Int = 0
)