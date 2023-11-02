package com.stathis.domain.usecases

import android.graphics.Bitmap
import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class UpdateDoctorImageUseCase @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<Boolean> {

    override suspend fun invoke(vararg args: Any?): Boolean {
        val bitmap = args.getOrNull(0) as Bitmap
        return repo.updateDoctorImage(bitmap)
    }
}