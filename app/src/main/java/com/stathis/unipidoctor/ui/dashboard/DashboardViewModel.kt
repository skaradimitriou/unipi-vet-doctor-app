package com.stathis.unipidoctor.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.UiModel
import com.stathis.domain.usecases.FetchDoctorInfoUseCase
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
    private val useCase: FetchDoctorInfoUseCase
) : BaseViewModel(app) {

    val data: LiveData<List<UiModel>>
        get() = _data

    private val _data = MutableLiveData<List<UiModel>>()

    fun getData(noOfAppointments : Int) {
        viewModelScope.launch(dispatcher) {
            val doctorInfo = useCase.invoke()
            val list = listOf(
                DashboardHeader(
                    imageUrl = doctorInfo.imageUrl,
                    username = doctorInfo.username
                ), DashboardCard(
                    title = "Today's appointments >",
                    subtitle = "You have $noOfAppointments appointments for today."
                ),
                DashboardDocCard(
                    title = "Doctor Card >",
                    subtitle = "Tap here to share your doctor card with your patients."
                )
            )

            _data.postValue(list)
        }
    }
}