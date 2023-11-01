package com.stathis.unipidoctor.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderDashboardCardBinding
import com.stathis.unipidoctor.databinding.HolderDashboardDocCardBinding
import com.stathis.unipidoctor.databinding.HolderDashboardHeaderBinding
import com.stathis.unipidoctor.databinding.HolderEmptyBinding
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardCard
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardDocCard
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardHeader

class DashboardAdapter(
    private val callback: DashboardScreenCallback
) : ListAdapter<UiModel, DashboardViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_dashboard_header -> {
                HolderDashboardHeaderBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_dashboard_card -> {
                HolderDashboardCardBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_dashboard_doc_card -> {
                HolderDashboardDocCardBinding.inflate(inflater, parent, false)
            }
            else -> HolderEmptyBinding.inflate(inflater, parent, false)
        }
        return DashboardViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is DashboardHeader -> R.layout.holder_dashboard_header
        is DashboardCard -> R.layout.holder_dashboard_card
        is DashboardDocCard -> R.layout.holder_dashboard_doc_card
        else -> R.layout.holder_empty
    }
}

class DashboardViewHolder(
    private val binding: ViewDataBinding,
    private val callback: DashboardScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is DashboardHeader, is DashboardCard, is DashboardDocCard -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
            else -> Unit
        }
    }
}

interface DashboardScreenCallback {
    fun onProfileIconClick()
    fun onAppointmentsClick()
    fun onDoctorCardClick()
}