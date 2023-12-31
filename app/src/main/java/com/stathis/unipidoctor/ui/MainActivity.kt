package com.stathis.unipidoctor.ui

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseActivity
import com.stathis.unipidoctor.databinding.ActivityMainBinding
import com.stathis.unipidoctor.navigation.NavigatorImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainSharedViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: NavigatorImpl

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavMenu.setupWithNavController(navController)
        navigator = NavigatorImpl(this, navController)
    }

    override fun startOps() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            val initScreens = listOf(R.id.nav_home, R.id.nav_analytics)
            val isAtInitScreens = initScreens.contains(destination.id)
            binding.shouldShowBottomNavBar = isAtInitScreens
            supportActionBar?.setDisplayHomeAsUpEnabled(!isAtInitScreens)
        }

        viewModel.navigatorState.observe(this) { navigationAction ->
            navigationAction?.let { model ->
                model.action?.let { navigator.navigateTo(it, model.args) }
            }
        }
    }

    override fun stopOps() {
        viewModel.navigatorState.removeObservers(this)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    override fun onBackPressed() {
        navigator.goBack()
        viewModel.resetNavigation()
    }
}