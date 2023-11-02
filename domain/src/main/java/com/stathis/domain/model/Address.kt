package com.stathis.domain.model

data class Address(
    val street: String,
    val number: String,
    val city: String,
    val postalCode: String
) {
    fun getAddressToDisplay() = "$street, $number, $city, $postalCode"
}