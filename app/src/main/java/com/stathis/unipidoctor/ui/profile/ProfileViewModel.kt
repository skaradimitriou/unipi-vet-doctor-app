package com.stathis.unipidoctor.ui.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.UiModel
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
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val data: LiveData<List<UiModel>>
        get() = _data

    private val _data = MutableLiveData<List<UiModel>>()

    fun getData() {
        viewModelScope.launch(dispatcher) {
            val list = listOf(
                ProfileHeader(
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.marvel.com%2Fcharacters%2Fthor-thor-odinson&psig=AOvVaw2RPWgEei8XY0KGLRzVJU62&ust=1698848586148000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPCe-6W-oIIDFQAAAAAdAAAAABAE",
                    "mydummyuser",
                    "User Useropoulos",
                ),
                ProfileCard(
                    R.drawable.ic_location,
                    "Address",
                    ProfileCardType.ADDRESS
                ),
                ProfileCard(
                    R.drawable.ic_time,
                    "Working hours",
                    ProfileCardType.WORKING_HOURS
                ),
                ProfileCard(
                    R.drawable.ic_phone,
                    "Contact info",
                    ProfileCardType.CONTACT_INFO
                )
            )

            _data.postValue(list)
        }
    }
}