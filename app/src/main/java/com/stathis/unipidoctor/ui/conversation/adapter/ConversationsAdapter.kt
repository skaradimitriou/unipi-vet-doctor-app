package com.stathis.unipidoctor.ui.conversation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.ChatMessage
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderMessageBinding

class ConversationsAdapter :
    ListAdapter<UiModel, ConversationsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationsViewHolder {
        val view =
            HolderMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConversationsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ConversationsViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {
    override fun present(data: UiModel) {
        when (data) {
            is ChatMessage -> {
                binding.setVariable(BR.model, data)
            }
        }
    }
}