package com.stathis.data.mappers

import com.stathis.data.dto.*
import com.stathis.data.utils.toNotNull
import com.stathis.domain.model.*

object DoctorInfoMapper : BaseMapper<DoctorInfoDto?, DoctorInfo> {

    override fun toDomainModel(dto: DoctorInfoDto?) = DoctorInfo(
        username = dto?.username.toNotNull(),
        fullName = dto?.fullName.toNotNull(),
        imageUrl = dto?.imageUrl.toNotNull(),
        details = dto?.details.toDomainModel(),
    )

    private fun DoctorDetailsDto?.toDomainModel() = DoctorDetails(
        address = this?.address.toDomainModel(),
        workingHours = this?.workingHours.toDomainModel(),
        contact = this?.contact.toDomainModel()
    )

    private fun AddressDto?.toDomainModel() = Address(
        street = this?.street.toNotNull(),
        number = this?.number.toNotNull(),
        city = this?.city.toNotNull(),
        postalCode = this?.postalCode.toNotNull()
    )

    private fun WorkingHoursDto?.toDomainModel() = WorkingHours(
        normal = this?.normal.toNotNull(),
        weekends = this?.normal.toNotNull()
    )

    private fun ContactDetailsDto?.toDomainModel() = ContactDetails(
        email = this?.email.toNotNull(),
        telephone = this?.telephone.toNotNull()
    )
}