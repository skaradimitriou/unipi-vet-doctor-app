package com.stathis.unipidoctor.ui.chatlanding

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentChatLandingBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.ui.chatlanding.adapter.ChatLandingAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatLandingFragment :
    BaseFragment<FragmentChatLandingBinding>(R.layout.fragment_chat_landing) {

    private val viewModel: ChatLandingViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    private val adapter = ChatLandingAdapter { conversation ->
        val args = Bundle().apply {
            putParcelable("CONVERSATION", conversation)
        }
        sharedVM.navigateToScreen(NavigationAction.CHAT_CONVERSATION, args = args)
    }

    override fun init() {
        setScreenTitle(getString(R.string.chat))

        binding.conversationsRecycler.apply {
            removeItemDecorations()
            adapter = this@ChatLandingFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }
    }

    override fun startOps() {
        viewModel.getConversations()

        viewModel.conversations.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun stopOps() {
        //
    }
}