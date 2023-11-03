package com.stathis.data.mappers

import com.stathis.data.dto.VetDto
import com.stathis.data.utils.toNotNull
import com.stathis.domain.model.Vet

object VetMapper : BaseMapper<List<VetDto>?, List<Vet>> {

    override fun toDomainModel(dto: List<VetDto>?): List<Vet> {
        return dto?.map { it.toDomainModel() } ?: listOf()
    }

    fun VetDto?.toDomainModel() = Vet(
        id = this?.id.toNotNull(),
        category = this?.category.toNotNull(),
        experience = this?.experience.toNotNull(),
        firstName = this?.firstName.toNotNull(),
        lastName = this?.lastName.toNotNull(),
        fullName = this?.fullName.toNotNull(),
        image = this?.image.toNotNull(),
        mobileNo = this?.mobileNo.toNotNull(),
        rating = this?.rating.toNotNull(),
    )
}