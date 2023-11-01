package com.stathis.unipidoctor.ui.dashboard.uimodel

import com.stathis.domain.model.UiModel

data class DashboardHeader(
    val imageUrl: String,
    val username: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DashboardHeader -> imageUrl == obj.imageUrl && username == obj.username
        else -> false
    }
}