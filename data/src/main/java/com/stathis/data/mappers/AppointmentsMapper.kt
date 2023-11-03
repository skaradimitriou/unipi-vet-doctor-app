package com.stathis.data.mappers

import com.stathis.data.dto.AppointmentDto
import com.stathis.data.mappers.VetMapper.toDomainModel
import com.stathis.data.utils.toNotNull
import com.stathis.domain.model.AppointmentInfo

object AppointmentsMapper : BaseMapper<List<AppointmentDto?>?, List<AppointmentInfo>> {

    override fun toDomainModel(dto: List<AppointmentDto?>?): List<AppointmentInfo> = dto?.map {
        AppointmentInfo(
            vet = it?.vet.toDomainModel(),
            appointmentDateAndTime = it?.appointmentDateAndTime.toNotNull(),
            uuid = it?.uuid.toNotNull(),
            firestoreId = it?.firestoreId.toNotNull()
        )
    } ?: listOf()
}