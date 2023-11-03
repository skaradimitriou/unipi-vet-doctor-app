package com.stathis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vet(
    val category: String,
    val experience: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val image: String,
    val mobileNo: String,
    var rating: Double,
    val id: Int
) : UiModel, Parcelable {
    override fun equalsContent(obj: UiModel): Boolean = false
}
