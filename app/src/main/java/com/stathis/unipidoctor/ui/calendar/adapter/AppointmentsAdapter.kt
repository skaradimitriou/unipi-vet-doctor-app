package com.stathis.unipidoctor.ui.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.stathis.domain.model.Appointment
import com.stathis.domain.model.UiModel
import com.stathis.unipidoctor.BR
import com.stathis.unipidoctor.abstraction.BaseViewHolder
import com.stathis.unipidoctor.abstraction.DiffUtilClass
import com.stathis.unipidoctor.databinding.HolderAppointmentBinding

class AppointmentsAdapter : ListAdapter<UiModel, AppointmentsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val view = HolderAppointmentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AppointmentsViewHolder(
    private val binding: ViewDataBinding
) : BaseViewHolder(binding) {
    override fun present(data: UiModel) {
        when (data) {
            is Appointment -> binding.setVariable(BR.model, data)
            else -> Unit
        }
    }
}