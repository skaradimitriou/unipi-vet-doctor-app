package com.stathis.domain.model

data class ChatMessage(
    val message: String
) : UiModel {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is ChatMessage -> message == obj.message
        else -> false
    }
}