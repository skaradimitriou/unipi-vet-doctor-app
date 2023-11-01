package com.stathis.unipidoctor.ui.changeaddress

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentAddressBinding
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>(R.layout.fragment_address) {

    private val viewModel: AddressViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    override fun init() {
        setScreenTitle("Change Address")
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}