package com.stathis.unipidoctor.ui

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseActivity
import com.stathis.unipidoctor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}