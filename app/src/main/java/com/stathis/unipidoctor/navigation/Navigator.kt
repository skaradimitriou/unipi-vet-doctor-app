package com.stathis.unipidoctor.navigation

import android.os.Bundle

interface Navigator {

    fun navigateTo(screenKey: NavigationAction, args: Bundle? = null)
    fun goBack()
}