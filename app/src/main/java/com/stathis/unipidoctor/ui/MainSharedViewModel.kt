package com.stathis.unipidoctor.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.domain.model.DoctorInfo
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val navigatorState: LiveData<NavigationAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<NavigationAction?>()

    val doctorData: LiveData<DoctorInfo?>
        get() = _doctorData

    private val _doctorData = MutableLiveData<DoctorInfo?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: NavigationAction?) {
        _navigatorState.postValue(action)
    }

    fun getDoctorData() {
        viewModelScope.launch(dispatcher) {
//            repository.getDoctorData().collect {
//                _doctorData.postValue(it)
//            }
        }
    }
}