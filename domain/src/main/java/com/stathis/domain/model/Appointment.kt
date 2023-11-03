package com.stathis.domain.model

import java.text.SimpleDateFormat

data class Appointment(
    val patientName: String,
    val patientImage: String,
    val dateAndTime: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Appointment -> patientImage == obj.patientImage && patientName == obj.patientName
                && dateAndTime == obj.dateAndTime
        else -> false
    }

    fun getDisplayedDate(): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val outputDate = input.parse(dateAndTime)
        val sdf = SimpleDateFormat("dd MMMM yyyy HH:mm")
        return sdf.format(outputDate).toString()
    }
}
