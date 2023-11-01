package com.stathis.unipidoctor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stathis.unipidoctor.navigation.NavigationAction

class MainSharedViewModel : ViewModel() {

    val navigatorState: LiveData<NavigationAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<NavigationAction?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: NavigationAction?) {
        _navigatorState.postValue(action)
    }
}