package com.stathis.domain.usecases

import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val repo: DoctorRepository
) {
    suspend fun getProfileInfo(uuid: String) = repo.getProfileInfo(uuid)
}