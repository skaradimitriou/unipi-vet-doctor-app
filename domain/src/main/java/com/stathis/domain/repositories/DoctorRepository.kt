package com.stathis.domain.repositories

import com.stathis.domain.model.DoctorInfo

interface DoctorRepository {
    suspend fun getDoctorData(): DoctorInfo
}