package com.stathis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatConversation(
    val image: String,
    val username: String,
    val lastMessage: String? = "..."
) : UiModel, Parcelable {
    override fun equalsContent(obj: UiModel) = when (obj) {
        is ChatConversation -> image == obj.image && username == obj.username && lastMessage == obj.lastMessage
        else -> false
    }
}
