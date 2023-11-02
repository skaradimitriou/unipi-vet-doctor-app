package com.stathis.unipidoctor.ui.dashboard

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentDashboardBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.ui.dashboard.adapter.DashboardAdapter
import com.stathis.unipidoctor.ui.dashboard.adapter.DashboardScreenCallback
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard),
    DashboardScreenCallback {

    private val viewModel: DashboardViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    private val adapter = DashboardAdapter(this)

    override fun init() {
        //FIXME: Remove hardcoded value when possible
        setScreenTitle("Home")

        binding.dashboardRecycler.apply {
            removeItemDecorations()
            adapter = this@DashboardFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }
    }

    override fun startOps() {
        viewModel.getData(noOfAppointments = sharedVM.todaysAppointments)
        viewModel.data.observe(viewLifecycleOwner) { data ->
            adapter.submitList(data)
        }
    }

    override fun stopOps() {}

    override fun onProfileIconClick() {
        sharedVM.navigateToScreen(NavigationAction.PROFILE)
    }

    override fun onAppointmentsClick() {
        sharedVM.navigateToScreen(NavigationAction.CALENDAR)
    }

    override fun onDoctorCardClick() {
        sharedVM.navigateToScreen(NavigationAction.SHOW_DOCTOR_QR)
    }
}