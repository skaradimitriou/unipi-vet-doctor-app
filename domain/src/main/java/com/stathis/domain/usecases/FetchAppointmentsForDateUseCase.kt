package com.stathis.domain.usecases

import com.stathis.domain.model.Appointment
import com.stathis.domain.repositories.DoctorRepository
import java.util.*
import javax.inject.Inject

class FetchAppointmentsForDateUseCase @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<List<Appointment>> {
    override suspend fun invoke(vararg args: Any?): List<Appointment> {
        val date = args.getOrNull(0) as? Date ?: Date()
        return repo.fetchAppointmentsForDate(date)
    }
}