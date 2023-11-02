package com.stathis.domain.model

data class ContactDetails(
    val email: String,
    val telephone: String
) {
    fun getContactDetails() = "E-mail: $email\nTel: $telephone"
}