package com.stathis.unipidoctor.ui.profile

import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentProfileBinding
import com.stathis.unipidoctor.ui.profile.adapter.ProfileAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val adapter = ProfileAdapter { selectedCard ->
        Timber.d("$selectedCard")
    }

    override fun init() {
        setScreenTitle(getString(R.string.profile))

        binding.vetsRecycler.apply {
            removeItemDecorations()
            adapter = this@ProfileFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getData()

        viewModel.data.observe(viewLifecycleOwner) { screenData ->
            adapter.submitList(screenData)
        }
    }

    override fun stopOps() {}
}