package com.stathis.unipidoctor.ui.changeaddress

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.FetchDoctorInfoUseCase
import com.stathis.domain.usecases.UpdateDoctorAddressUseCase
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getProfileInfoUseCase: FetchDoctorInfoUseCase,
    private val updateAddressUseCase: UpdateDoctorAddressUseCase
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

    private var tempStreetName: String? = null
    private var tempNumber: String? = null
    private var tempPostalCode: String? = null
    private var tempCity: String? = null

    init {
        getProfileInfo()
    }

    private fun getProfileInfo() {
        viewModelScope.launch(dispatcher) {
            val result = getProfileInfoUseCase.invoke()

            tempStreetName = result.details.address.street
            tempNumber = result.details.address.number
            tempPostalCode = result.details.address.postalCode
            tempCity = result.details.address.city

            val model = UpdateInfoModel(
                result.details.address.street,
                result.details.address.number,
                result.details.address.postalCode,
                result.details.address.city
            )
            _userInfo.postValue(model)
        }
    }

    fun validateInput(
        streetName: String? = null,
        number: String? = null,
        postalCode: String? = null,
        city: String? = null
    ) {

        streetName?.let { tempStreetName = it }
        number?.let { tempNumber = it }
        postalCode?.let { tempPostalCode = it }
        city?.let { tempCity = it }
    }

    data class UpdateInfoModel(
        val streetName: String,
        val number: String,
        val postalCode: String,
        val city: String
    )

    fun saveUserData() {
        viewModelScope.launch(dispatcher) {
            val result =
                updateAddressUseCase.invoke(tempStreetName, tempNumber, tempPostalCode, tempCity)
            _profileUpdated.postValue(result)
        }
    }
}