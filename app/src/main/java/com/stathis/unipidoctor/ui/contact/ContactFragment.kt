package com.stathis.unipidoctor.ui.contact

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentContactBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact) {

    private val viewModel: ContactViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.update_contact))

        binding.viewModel = viewModel

        binding.emailInputTxt.doAfterTextChanged {
            viewModel.validateInput(email = it.toString())
        }

        binding.telephoneInputTxt.doAfterTextChanged {
            viewModel.validateInput(telephone = it.toString())
        }

        binding.saveBtn.setOnClickListener {
            viewModel.saveUserData()
        }
    }

    override fun startOps() {
        viewModel.userInfo.observe(viewLifecycleOwner) { model ->
            binding.emailInputTxt.setText(model.email)
            binding.telephoneInputTxt.setText(model.telephone)
        }

        viewModel.profileUpdated.observe(viewLifecycleOwner) {
            sharedVM.navigateToScreen(NavigationAction.CONTACT_INFO_UPDATED)
        }
    }

    override fun stopOps() {}
}