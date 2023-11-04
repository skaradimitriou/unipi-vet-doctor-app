package com.stathis.domain.model

data class Address(
    var street: String,
    var number: String,
    var city: String,
    var postalCode: String
) {
    fun getAddressToDisplay() = "$street, $number, $city, $postalCode"
}