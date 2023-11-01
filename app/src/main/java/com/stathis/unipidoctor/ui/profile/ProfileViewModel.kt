package com.stathis.unipidoctor.ui.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.UiModel
import com.stathis.domain.usecases.FetchDoctorInfoUseCase
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.ui.profile.adapter.ProfileCard
import com.stathis.unipidoctor.ui.profile.adapter.ProfileCardType
import com.stathis.unipidoctor.ui.profile.adapter.ProfileHeader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchDoctorInfoUseCase
) : BaseViewModel(app) {

    val data: LiveData<List<UiModel>>
        get() = _data

    private val _data = MutableLiveData<List<UiModel>>()

    fun getData() {
        viewModelScope.launch(dispatcher) {
            val doctorInfo = useCase.invoke()
            val list = listOf(
                ProfileHeader(
                    imageUrl = doctorInfo.imageUrl,
                    username = doctorInfo.username,
                    fullName = doctorInfo.fullName
                ),
                ProfileCard(
                    R.drawable.ic_location,
                    "Address",
                    doctorInfo.details.address.toString(),
                    ProfileCardType.ADDRESS
                ),
                ProfileCard(
                    R.drawable.ic_time,
                    "Working hours",
                    doctorInfo.details.workingHours.toString(),
                    ProfileCardType.WORKING_HOURS
                ),
                ProfileCard(
                    R.drawable.ic_phone,
                    "Contact info",
                    doctorInfo.details.contact.toString(),
                    ProfileCardType.CONTACT_INFO
                )
            )

            _data.postValue(list)
        }
    }
}