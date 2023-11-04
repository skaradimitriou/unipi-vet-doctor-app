package com.stathis.unipidoctor.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.UiModel
import com.stathis.domain.usecases.FetchDoctorAppointmentsUseCase
import com.stathis.domain.usecases.FetchDoctorInfoUseCase
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardCard
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardDocCard
import com.stathis.unipidoctor.ui.dashboard.uimodel.DashboardHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchDoctorInfoUseCase,
    private val appointmentsUseCase : FetchDoctorAppointmentsUseCase
) : BaseViewModel(app) {

    val data: LiveData<List<UiModel>>
        get() = _data

    private val _data = MutableLiveData<List<UiModel>>()

    fun getData() {
        viewModelScope.launch(dispatcher) {
            val doctorInfo = useCase.invoke()

            val appointmentDesc = if(appointmentsUseCase.invoke().isEmpty()) {
                "You have no appointments for today"
            } else {
                "You have ${appointmentsUseCase.invoke().size} appointments for today."
            }

            val list = listOf(
                DashboardHeader(
                    imageUrl = doctorInfo.imageUrl,
                    username = doctorInfo.username
                ), DashboardCard(
                    title = "Appointments",
                    subtitle = appointmentDesc,
                    image = R.drawable.calendar
                ),
                DashboardDocCard(
                    title = "Doctor QR Card",
                    subtitle = "Tap here to share your doctor card with your patients.",
                    image = R.drawable.dummy_qr
                )
            )

            _data.postValue(list)
        }
    }
}