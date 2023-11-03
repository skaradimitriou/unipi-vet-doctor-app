package com.stathis.domain.model

data class ContactDetails(
    var email: String,
    var telephone: String
) {
    fun getContactDetails() = "E-mail: $email\nTel: $telephone"
}