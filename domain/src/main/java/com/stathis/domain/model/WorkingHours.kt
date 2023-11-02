package com.stathis.domain.model

data class WorkingHours(
    val normal: String,
    val weekends: String
) {
    fun getWorkingHours() = "Normal Days: $normal\nWeekends: $weekends"
}