package com.stathis.unipidoctor.ui.analytics

import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentAnalyticsBinding
import com.stathis.unipidoctor.ui.analytics.adapter.AnalyticsAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import com.stathis.unipidoctor.utils.setupChart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalyticsFragment : BaseFragment<FragmentAnalyticsBinding>(R.layout.fragment_analytics) {

    private val viewModel: AnalyticsViewModel by viewModels()
    private val adapter = AnalyticsAdapter()

    override fun init() {
        setScreenTitle("Analytics")

        binding.analyticsRecycler.apply {
            removeItemDecorations()
            adapter = this@AnalyticsFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }
    }

    override fun startOps() {
        viewModel.populateChart()

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.dataset.observe(viewLifecycleOwner) { dataset ->
            binding.chart.setupChart(list = dataset)
        }
    }

    override fun stopOps() {}
}