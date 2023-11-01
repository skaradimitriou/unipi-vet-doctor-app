package com.stathis.unipidoctor.navigation

interface Navigator {

    fun navigateTo(screenKey: NavigationAction)
    fun goBack()
}