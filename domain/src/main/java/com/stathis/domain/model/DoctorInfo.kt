package com.stathis.domain.model

data class DoctorInfo(
    val username: String,
    val fullName: String,
    val imageUrl: String,
    val details: DoctorDetails,
)