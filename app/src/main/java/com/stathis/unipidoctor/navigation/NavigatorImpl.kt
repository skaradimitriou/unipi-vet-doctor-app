package com.stathis.unipidoctor.navigation

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import com.stathis.unipidoctor.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : Navigator {

    override fun navigateTo(screenKey: NavigationAction, args: Bundle?) = when (screenKey) {
        NavigationAction.PROFILE -> navController.navigate(R.id.profileFragment)
        NavigationAction.UPLOAD_PHOTO -> navController.navigate(R.id.uploadImageFragment)
        NavigationAction.PHOTO_UPLOADED -> goBack()
        NavigationAction.UPDATE_CONTACT_INFO -> navController.navigate(R.id.contactFragment)
        NavigationAction.CONTACT_INFO_UPDATED -> goBack()
        NavigationAction.SHOW_DOCTOR_QR -> navController.navigate(R.id.doctorCardFragment)
        NavigationAction.CALENDAR -> navController.navigate(R.id.calendarFragment)
        NavigationAction.CHAT_CONVERSATION -> navController.navigate(R.id.conversationFragment, args)
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