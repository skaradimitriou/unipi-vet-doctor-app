package com.stathis.domain.usecases

import com.stathis.domain.model.Appointment
import com.stathis.domain.repositories.DoctorRepository
import javax.inject.Inject

class FetchDoctorAppointmentsUseCase @Inject constructor(
    private val repo: DoctorRepository
) : BaseUseCase<List<Appointment>> {
    override suspend fun invoke(vararg args: Any?) = repo.getDoctorAppointments()
}