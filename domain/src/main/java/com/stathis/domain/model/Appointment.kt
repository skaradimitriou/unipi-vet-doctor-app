package com.stathis.domain.model

data class Appointment(
    val patientName: String,
    val patientImage: String,
    val date: String,
    val time: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is Appointment -> patientImage == obj.patientImage && patientName == obj.patientName
                && date == obj.date && time == obj.time
        else -> false
    }
}
