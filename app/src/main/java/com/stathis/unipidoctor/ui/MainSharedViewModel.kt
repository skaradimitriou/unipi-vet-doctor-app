package com.stathis.unipidoctor.ui

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val navigatorState: LiveData<NavigationInfo?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<NavigationInfo?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: NavigationAction?, args: Bundle? = null) {
        val navigationAction = NavigationInfo(action, args)
        _navigatorState.postValue(navigationAction)
    }

    data class NavigationInfo(
        val action: NavigationAction? = null,
        val args: Bundle? = null
    )
}