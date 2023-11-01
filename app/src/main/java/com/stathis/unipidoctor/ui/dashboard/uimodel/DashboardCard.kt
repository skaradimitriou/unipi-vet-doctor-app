package com.stathis.unipidoctor.ui.dashboard.uimodel

import com.stathis.domain.model.UiModel

data class DashboardCard(
    val title: String,
    val subtitle: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is DashboardCard -> title == obj.title && subtitle == obj.subtitle
        else -> false
    }
}
