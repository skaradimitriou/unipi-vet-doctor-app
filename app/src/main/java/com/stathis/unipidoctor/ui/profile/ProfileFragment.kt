package com.stathis.unipidoctor.ui.profile

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentProfileBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.ui.profile.adapter.ProfileAdapter
import com.stathis.unipidoctor.ui.profile.adapter.ProfileCard
import com.stathis.unipidoctor.ui.profile.adapter.ProfileCardType
import com.stathis.unipidoctor.ui.profile.adapter.ProfileScreenCallback
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    private val adapter = ProfileAdapter(object : ProfileScreenCallback {
        override fun onProfileClick() {
            sharedVM.navigateToScreen(NavigationAction.UPLOAD_PHOTO)
        }

        override fun onCardClick(card: ProfileCard) {
            when (card.type) {
                ProfileCardType.ADDRESS -> sharedVM.navigateToScreen(NavigationAction.ADDRESS)
                ProfileCardType.CONTACT_INFO -> sharedVM.navigateToScreen(NavigationAction.UPDATE_CONTACT_INFO)
                else -> Unit
            }
        }
    })

    override fun init() {
        setScreenTitle(getString(R.string.profile))

        binding.vetsRecycler.apply {
            removeItemDecorations()
            adapter = this@ProfileFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }
    }

    override fun startOps() {
        viewModel.getData()

        viewModel.data.observe(viewLifecycleOwner) { screenData ->
            adapter.submitList(screenData)
        }
    }

    override fun stopOps() {}
}