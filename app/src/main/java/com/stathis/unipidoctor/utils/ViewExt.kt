package com.stathis.unipidoctor.utils

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Helper method to set the screen title inside a [Fragment] in a more simple way.
 */

fun Fragment.setScreenTitle(title: String) {
    requireActivity().title = title
}

/**
 * Helper fun to simplify the addition of a custom menu inside a [Fragment].
 */

fun Fragment.addAppBarMenu(menuId: Int, selectedOption: (Int) -> Unit) {
    val menuHost: MenuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Add menu items here
            menuInflater.inflate(menuId, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            selectedOption.invoke(menuItem.itemId)
            return true
        }
    }, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

/**
 * Helper fun to add delay to a simple task
 */

fun Fragment.withDelay(time: Long, action: () -> Unit) {
    lifecycleScope.launch {
        delay(time)
        action.invoke()
    }
}