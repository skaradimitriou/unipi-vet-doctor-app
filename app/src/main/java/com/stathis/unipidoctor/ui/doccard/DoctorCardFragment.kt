package com.stathis.unipidoctor.ui.doccard

import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentDoctorCardBinding
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCardFragment : BaseFragment<FragmentDoctorCardBinding>(R.layout.fragment_doctor_card) {

    private val viewModel: DoctorCardViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.doctor_card_title))
    }

    override fun startOps() {
        viewModel.getDoctorInfo()

        viewModel.doctorCard.observe(viewLifecycleOwner) { docDetails ->
            binding.model = docDetails
        }
        viewModel.qr.observe(viewLifecycleOwner) { qrBitmap ->
            binding.qrImgView.setImageBitmap(qrBitmap)
        }
    }

    override fun stopOps() {}
}