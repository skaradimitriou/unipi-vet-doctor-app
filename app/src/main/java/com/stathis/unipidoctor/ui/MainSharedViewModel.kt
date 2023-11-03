package com.stathis.unipidoctor.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val navigatorState: LiveData<NavigationAction?>
        get() = _navigatorState

    private val _navigatorState = MutableLiveData<NavigationAction?>()

    fun resetNavigation() = _navigatorState.postValue(null)

    fun navigateToScreen(action: NavigationAction?) {
        _navigatorState.postValue(action)
    }
}