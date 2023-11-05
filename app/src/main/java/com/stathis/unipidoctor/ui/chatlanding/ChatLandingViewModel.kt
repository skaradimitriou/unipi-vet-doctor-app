package com.stathis.unipidoctor.ui.chatlanding

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stathis.domain.model.ChatConversation
import com.stathis.unipidoctor.abstraction.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatLandingViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val conversations: LiveData<List<ChatConversation>>
        get() = _conversations

    private val _conversations = MutableLiveData<List<ChatConversation>>()

    fun getConversations() {
        val list = listOf(
            ChatConversation(
                image = "https://firebasestorage.googleapis.com/v0/b/unipi-vet-app.appspot.com/o/profile%2FrsSzZLsXusfQUblyxZUtGtQjRfy2?alt=media&token=18aa47e0-c9d3-4455-a69a-97364ad96549",
                username = "τεστ τεστόπουλος",
                lastMessage = "...",
            )
        )
        _conversations.postValue(list)
    }
}