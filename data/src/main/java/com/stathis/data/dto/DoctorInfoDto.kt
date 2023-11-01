package com.stathis.data.dto

data class DoctorInfoDto(
    val username: String? = null,
    val fullName: String? = null,
    val imageUrl: String? = null,
    val details: DoctorDetailsDto? = null
)