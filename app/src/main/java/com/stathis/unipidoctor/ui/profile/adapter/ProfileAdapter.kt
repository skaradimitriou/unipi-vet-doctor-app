package com.stathis.unipidoctor.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderEmptyBinding
import com.stathis.unipidoctor.databinding.HolderProfileCardBinding
import com.stathis.unipidoctor.databinding.HolderProfileHeaderBinding

class ProfileAdapter(private val callback: ProfileScreenCallback) :
    ListAdapter<UiModel, ProfileViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            R.layout.holder_profile_header -> HolderProfileHeaderBinding.inflate(
                inflater,
                parent,
                false
            )
            R.layout.holder_profile_card -> HolderProfileCardBinding.inflate(
                inflater,
                parent,
                false
            )
            else -> HolderEmptyBinding.inflate(inflater, parent, false)
        }

        return ProfileViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ProfileHeader -> R.layout.holder_profile_header
        is ProfileCard -> R.layout.holder_profile_card
        else -> R.layout.holder_empty
    }
}

class ProfileViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ProfileScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is ProfileHeader -> binding.setVariable(BR.model, data)
            is ProfileCard -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            else -> Unit
        }
    }
}

fun interface ProfileScreenCallback {
    fun onCardClick(card: ProfileCard)
}