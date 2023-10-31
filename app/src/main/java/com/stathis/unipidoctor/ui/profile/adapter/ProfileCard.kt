package com.stathis.unipidoctor.ui.profile.adapter

import com.stathis.domain.model.UiModel

data class ProfileCard(
    val icon: Int,
    val text: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileCard -> icon == obj.icon && text == obj.text
        else -> false
    }
}