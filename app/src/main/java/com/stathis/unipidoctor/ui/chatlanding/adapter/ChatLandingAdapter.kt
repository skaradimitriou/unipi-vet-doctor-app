package com.stathis.unipidoctor.ui.chatlanding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.ChatConversation
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderChatConvoBinding

class ChatLandingAdapter(
    private val callback: ChatLandingCallback
) : ListAdapter<UiModel, ChatLandingViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatLandingViewHolder {
        val view =HolderChatConvoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatLandingViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ChatLandingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ChatLandingViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ChatLandingCallback
) : BaseViewHolder(binding) {
    override fun present(data: UiModel) {
        when (data) {
            is ChatConversation -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface ChatLandingCallback {
    fun onConversationClick(conversation: ChatConversation)
}