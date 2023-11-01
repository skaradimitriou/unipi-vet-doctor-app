package com.stathis.domain.usecases

import com.stathis.domain.model.DoctorInfo
import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class FetchDoctorInfoUseCase @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<DoctorInfo> {
    override suspend fun invoke(vararg args: Any?) = repo.getDoctorData()
}