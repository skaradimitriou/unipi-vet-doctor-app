package com.stathis.unipidoctor.ui

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
        navigator = NavigatorImpl(this, navController)
    }

    override fun startOps() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            val isAtInitScreens = destination.id == R.id.dashboardFragment
            binding.shouldShowBottomNavBar = isAtInitScreens
            supportActionBar?.setDisplayHomeAsUpEnabled(!isAtInitScreens)
        }

        viewModel.navigatorState.observe(this) { action ->
            action?.let { navigator.navigateTo(it) }
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