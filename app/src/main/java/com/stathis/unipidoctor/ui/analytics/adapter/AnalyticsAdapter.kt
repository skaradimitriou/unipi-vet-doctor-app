package com.stathis.unipidoctor.ui.analytics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderAnalyticsItemBinding
import com.stathis.unipidoctor.ui.analytics.uimodel.AnalyticsModel

class AnalyticsAdapter : ListAdapter<UiModel, AnalyticsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalyticsViewHolder {
        val view = HolderAnalyticsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AnalyticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnalyticsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AnalyticsViewHolder(private val binding: ViewDataBinding) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is AnalyticsModel -> binding.setVariable(BR.model, data)
        }
    }
}