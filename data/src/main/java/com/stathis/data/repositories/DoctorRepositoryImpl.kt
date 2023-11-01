package com.stathis.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.stathis.data.dto.DoctorInfoDto
import com.stathis.data.mappers.DoctorInfoMapper
import com.stathis.data.utils.toListOf
import com.stathis.domain.model.DoctorInfo
import com.stathis.domain.repositories.DoctorRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : DoctorRepository {

    override suspend fun getDoctorData(): DoctorInfo {
        val result = firestore.collection("doctor")
            .get()
            .await()
            .toListOf<DoctorInfoDto>()
            .getOrNull(0)

        return DoctorInfoMapper.toDomainModel(result)
    }
}