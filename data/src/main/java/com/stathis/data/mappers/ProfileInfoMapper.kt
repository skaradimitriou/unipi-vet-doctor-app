package com.stathis.data.mappers

import com.stathis.data.dto.UserInfoDto
import com.stathis.data.utils.toNotNull
import com.stathis.domain.model.UserInfo

object ProfileInfoMapper : BaseMapper<UserInfoDto?, UserInfo> {

    override fun toDomainModel(dto: UserInfoDto?): UserInfo = UserInfo(
        firstName = dto?.firstName.toNotNull(),
        lastName = dto?.lastName.toNotNull(),
        telephone = dto?.telephone.toNotNull(),
        email = dto?.email.toNotNull(),
        userImg = dto?.userImg.toNotNull(),
        username = dto?.username.toNotNull()
    )
}