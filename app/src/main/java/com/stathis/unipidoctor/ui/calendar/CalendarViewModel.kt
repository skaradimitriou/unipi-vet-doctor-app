package com.stathis.unipidoctor.ui.calendar

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.Appointment
import com.stathis.domain.usecases.FetchAppointmentsForDateUseCase
import com.stathis.domain.usecases.FetchDoctorAppointmentsUseCase
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchDoctorAppointmentsUseCase,
    private val appointmentsUseCase: FetchAppointmentsForDateUseCase,
) : BaseViewModel(app) {

    val appointments: LiveData<List<Appointment>>
        get() = _appointments

    private val _appointments = MutableLiveData<List<Appointment>>()

    fun fetchDoctorAppointments() {
        viewModelScope.launch(dispatcher) {
            val result = useCase.invoke()
            _appointments.postValue(result)
        }
    }

    fun getAppointmentsForDate(dateClicked: Date) {
        viewModelScope.launch(dispatcher) {
            val result = appointmentsUseCase.invoke(dateClicked)
            _appointments.postValue(result)
        }
    }
}