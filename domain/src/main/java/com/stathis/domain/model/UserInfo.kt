package com.stathis.domain.model

data class UserInfo(
    val firstName: String,
    val lastName: String,
    val telephone: String,
    val email: String,
    val userImg: String,
    val username: String,
    var hasUnreadNotifiactions: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is UserInfo -> email == obj.email && username == obj.username
        else -> false
    }
}
