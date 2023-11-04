package com.stathis.domain.usecases

import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class UpdateDoctorAddressUseCase  @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<Boolean> {

    override suspend fun invoke(vararg args: Any?): Boolean {
        val streetName = args.getOrNull(0) as? String ?: ""
        val number = args.getOrNull(1) as? String ?: ""
        val postalCode= args.getOrNull(2) as? String ?: ""
        val city = args.getOrNull(3) as? String ?: ""
        return repo.updateDoctorAddress(streetName, number, postalCode, city)
    }
}