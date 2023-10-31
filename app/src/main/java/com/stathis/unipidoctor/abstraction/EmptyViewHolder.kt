package com.stathis.unipidoctor.abstraction

import androidx.databinding.ViewDataBinding
import com.stathis.domain.model.UiModel

class EmptyViewHolder(
    binding: ViewDataBinding
) : BaseViewHolder(binding) {
    override fun present(data: UiModel) {

    }
}