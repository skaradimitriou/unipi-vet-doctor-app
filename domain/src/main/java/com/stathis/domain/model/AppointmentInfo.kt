package com.stathis.domain.model

import java.text.SimpleDateFormat

data class AppointmentInfo(
    val vet: Vet,
    val appointmentDateAndTime: String,
    val uuid: String,
    val firestoreId: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is AppointmentInfo -> appointmentDateAndTime == obj.appointmentDateAndTime
        else -> false
    }

    fun getDisplayedDate(): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val outputDate = input.parse(appointmentDateAndTime)
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm")
        return sdf.format(outputDate).toString()
    }
}