package com.stathis.unipidoctor.ui.contact

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.FetchDoctorInfoUseCase
import com.stathis.domain.usecases.UpdateDoctorContactDetailsUseCase
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getProfileInfoUseCase: FetchDoctorInfoUseCase,
    private val updateProfileInfoUseCase: UpdateDoctorContactDetailsUseCase
) : BaseViewModel(app) {

    val profileUpdated: LiveData<Boolean>
        get() = _profileUpdated

    private val _profileUpdated = MutableLiveData<Boolean>()

    val userInfo: LiveData<UpdateInfoModel>
        get() = _userInfo

    private val _userInfo = MutableLiveData<UpdateInfoModel>()

    val buttonState: LiveData<Boolean>
        get() = _buttonState

    private val _buttonState = MutableLiveData(true)

    private var tempEmail: String? = null
    private var tempTelephone: String? = null

    init {
        getProfileInfo()
    }

    private fun getProfileInfo() {
        viewModelScope.launch(dispatcher) {
            val result = getProfileInfoUseCase.invoke()
            val model =
                UpdateInfoModel(result.details.contact.email, result.details.contact.telephone)
            _userInfo.postValue(model)
        }
    }

    fun validateInput(email: String? = null, telephone: String? = null) {
        if (!email.isNullOrEmpty() && email != tempEmail) {
            tempEmail = email
        }

        tempTelephone = telephone
    }

    data class UpdateInfoModel(val email: String, val telephone: String)

    fun saveUserData() {
        viewModelScope.launch(dispatcher) {
            val result = updateProfileInfoUseCase.invoke(tempEmail, tempTelephone)
            _profileUpdated.postValue(result)
        }
    }
}