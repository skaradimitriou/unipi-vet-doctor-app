package com.stathis.domain.repositories

import android.graphics.Bitmap
import com.stathis.domain.model.DoctorInfo

interface DoctorRepository {
    suspend fun getDoctorData(): DoctorInfo
    suspend fun updateDoctorImage(bitmap: Bitmap) : Boolean
}