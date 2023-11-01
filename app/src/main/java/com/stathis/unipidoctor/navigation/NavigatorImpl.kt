package com.stathis.unipidoctor.navigation

import android.app.Activity
import androidx.navigation.NavController
import com.stathis.unipidoctor.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : Navigator {

    override fun navigateTo(screenKey: NavigationAction) = when (screenKey) {
        NavigationAction.PROFILE -> navController.navigate(R.id.profileFragment)
        NavigationAction.SHOW_DOCTOR_QR -> navController.navigate(R.id.doctorCardFragment)
        NavigationAction.CALENDAR -> navController.navigate(R.id.calendarFragment)
        NavigationAction.ADDRESS -> navController.navigate(R.id.addressFragment)
        else -> Unit
    }

    override fun goBack() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            activity?.finish()
        } else {
            navController.navigateUp()
        }
    }
}