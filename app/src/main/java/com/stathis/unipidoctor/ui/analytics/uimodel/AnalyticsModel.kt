package com.stathis.unipidoctor.ui.analytics.uimodel

import com.stathis.domain.model.UiModel

data class AnalyticsModel(
    val title: String,
    val description: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is AnalyticsModel -> title == obj.title && description == obj.description
        else -> false
    }
}
