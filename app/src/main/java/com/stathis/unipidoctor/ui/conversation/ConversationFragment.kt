package com.stathis.unipidoctor.ui.conversation

import androidx.fragment.app.viewModels
import com.stathis.domain.model.ChatConversation
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentConversationBinding
import com.stathis.unipidoctor.ui.conversation.adapter.ConversationsAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import com.stathis.unipidoctor.utils.withDelay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversationFragment :
    BaseFragment<FragmentConversationBinding>(R.layout.fragment_conversation) {

    private val viewModel: ConversationViewModel by viewModels()

    private val adapter = ConversationsAdapter()

    override fun init() {
        val conversationDetails = arguments?.getParcelable<ChatConversation>("CONVERSATION")
        conversationDetails?.let {
            setScreenTitle(it.username)
        }

        binding.messagesRecycler.apply {
            removeItemDecorations()
            adapter = this@ConversationFragment.adapter
            addItemDecoration(VerticalItemDecoration(40))
        }

        binding.sendBtn.setOnClickListener {
            val newMessage = binding.chatMessageInputTxt.text.toString()
            viewModel.saveNewMessage(newMessage)
            binding.chatMessageInputTxt.text.clear()
        }
    }

    override fun startOps() {
        viewModel.conversations.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            withDelay(200) {
                binding.messagesRecycler.scrollToPosition(adapter.currentList.size)
            }
        }
    }

    override fun stopOps() {}
}