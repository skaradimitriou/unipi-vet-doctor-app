package com.stathis.domain.usecases

import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class UpdateDoctorContactDetailsUseCase @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<Boolean> {

    override suspend fun invoke(vararg args: Any?): Boolean {
        val email = args.getOrNull(0) as? String ?: ""
        val telephone = args.getOrNull(1) as? String ?: ""
        return repo.updateDoctorDetails(email, telephone)
    }
}