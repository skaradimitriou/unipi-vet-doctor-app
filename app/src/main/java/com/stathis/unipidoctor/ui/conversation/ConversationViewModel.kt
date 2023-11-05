package com.stathis.unipidoctor.ui.conversation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stathis.domain.model.ChatMessage
import com.stathis.unipidoctor.abstraction.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val conversations: LiveData<List<ChatMessage>>
        get() = _conversations

    private val _conversations = MutableLiveData<List<ChatMessage>>()

    private val tempList = mutableListOf<ChatMessage>()

    fun saveNewMessage(newMessage: String) {
        tempList.add(ChatMessage(newMessage))
        _conversations.postValue(tempList)
    }
}
