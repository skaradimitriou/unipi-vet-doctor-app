package com.stathis.unipidoctor.ui.profile.adapter

import com.stathis.domain.model.UiModel

data class ProfileHeader(
    val imageUrl: String,
    val username: String,
    val fullName: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileHeader -> imageUrl == obj.imageUrl && username == obj.username && fullName == obj.fullName
        else -> false
    }
}
