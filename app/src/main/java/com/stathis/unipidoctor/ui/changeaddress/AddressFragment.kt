package com.stathis.unipidoctor.ui.changeaddress

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentAddressBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>(R.layout.fragment_address) {

    private val viewModel: AddressViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.update_address))

        binding.viewModel = viewModel

        binding.streetInputTxt.doAfterTextChanged {
            viewModel.validateInput(streetName = it.toString())
        }

        binding.numberInputTxt.doAfterTextChanged {
            viewModel.validateInput(number = it.toString())
        }

        binding.postalCodeInputTxt.doAfterTextChanged {
            viewModel.validateInput(postalCode = it.toString())
        }

        binding.cityInputTxt.doAfterTextChanged {
            viewModel.validateInput(city = it.toString())
        }

        binding.saveBtn.setOnClickListener {
            viewModel.saveUserData()
        }
    }

    override fun startOps() {
        viewModel.userInfo.observe(viewLifecycleOwner) { model ->
            binding.streetInputTxt.setText(model.streetName)
            binding.numberInputTxt.setText(model.number)
            binding.postalCodeInputTxt.setText(model.postalCode)
            binding.cityInputTxt.setText(model.city)
        }

        viewModel.profileUpdated.observe(viewLifecycleOwner) {
            sharedVM.navigateToScreen(NavigationAction.CONTACT_INFO_UPDATED)
        }
    }

    override fun stopOps() {}
}