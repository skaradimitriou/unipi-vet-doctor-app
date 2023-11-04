package com.stathis.domain.repositories

import android.graphics.Bitmap
import com.stathis.domain.model.Appointment
import com.stathis.domain.model.DoctorInfo
import com.stathis.domain.model.UserInfo
import java.util.*

interface DoctorRepository {
    suspend fun getDoctorData(): DoctorInfo
    suspend fun getDoctorAppointments(): List<Appointment>
    suspend fun fetchAppointmentsForDate(date: Date): List<Appointment>
    suspend fun updateDoctorImage(bitmap: Bitmap): Boolean
    suspend fun updateDoctorDetails(email: String, telephone: String): Boolean

    suspend fun updateDoctorAddress(
        streetName: String,
        number: String,
        postalCode: String,
        city: String
    ): Boolean

    suspend fun getProfileInfo(uuid: String): UserInfo
}