package com.stathis.unipidoctor.ui.dashboard.uimodel

import com.stathis.domain.model.UiModel

data class DashboardDocCard(
    val title: String,
    val subtitle: String,
    val image : Int
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DashboardDocCard -> title == obj.title && subtitle == obj.subtitle
        else -> false
    }
}