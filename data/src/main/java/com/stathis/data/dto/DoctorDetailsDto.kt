package com.stathis.data.dto

data class DoctorDetailsDto(
    val address: AddressDto? = null,
    val workingHours: WorkingHoursDto? = null,
    val contact: ContactDetailsDto? = null
)