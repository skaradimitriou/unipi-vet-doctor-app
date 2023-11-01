package com.stathis.unipidoctor.ui.profile.adapter

import com.stathis.domain.model.UiModel

data class ProfileCard(
    val icon: Int,
    val text: String,
    val description: String,
    val type: ProfileCardType
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileCard -> icon == obj.icon && text == obj.text && description == obj.description && type == obj.type
        else -> false
    }
}

enum class ProfileCardType {
    ADDRESS,
    WORKING_HOURS,
    CONTACT_INFO
}