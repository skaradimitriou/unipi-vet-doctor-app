package com.stathis.unipidoctor.ui.image

import android.content.Intent
import android.provider.MediaStore
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentUploadImageBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.utils.*
import com.stathis.unipidoctor.utils.components.GenericBottomSheet
import com.stathis.unipidoctor.utils.components.GenericBottomSheet.Companion.GENERIC_BS_TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadImageFragment :
    BaseFragment<FragmentUploadImageBinding>(R.layout.fragment_upload_image) {

    private val viewModel: UploadImageViewModel by viewModels()
    private val activityViewModel: MainSharedViewModel by activityViewModels()

    private val cameraIntent = onSuccessCameraResult { bitmap ->
        bitmap?.let {
            viewModel.saveUserPhoto(it)
        }
    }

    private val galleryIntent = onSuccessResult { result ->
        result.data?.data?.let {
            viewModel.saveUserPhoto(it.toBitmap(requireContext()))
        }
    }

    override fun init() {
        setScreenTitle("Update Doctor Image")
        viewModel.getUserImage()

        binding.addImgBtn.setOnClickListener {
            showUploadOptions()
        }

        binding.saveBtn.setOnClickListener {
            viewModel.saveUserImage()
        }
    }

    private fun showUploadOptions() {
        GenericBottomSheet.Builder()
            .setTitle("Upload new image")
            .setFirstOption("Take a photo")
            .setSecondOption("Select from gallery")
            .setListener(object : GenericBottomSheet.GenericBottomSheetListener {
                override fun onFirstOptionClick() {
                    capturePhoto()
                }

                override fun onSecondOptionClick() {
                    uploadFromGallery()
                }
            })
            .build()
            .show(requireActivity().supportFragmentManager, GENERIC_BS_TAG)
    }

    override fun startOps() {
        viewModel.ctaState.observe(viewLifecycleOwner) { isEnabled ->
            binding.saveBtn.isEnabled = isEnabled
        }

        viewModel.userImage.observe(viewLifecycleOwner) { url ->
            binding.userImgView.setProfileImage(url)
        }

        viewModel.bitmap.observe(viewLifecycleOwner) { bitmap ->
            binding.userImgView.setImageBitmap(bitmap)
        }

        viewModel.bitmapSaved.observe(viewLifecycleOwner) { result ->
            activityViewModel.navigateToScreen(NavigationAction.PHOTO_UPLOADED)
        }
    }

    override fun stopOps() {
        activityViewModel.resetNavigation()
    }

    private fun capturePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.launch(intent)
    }

    private fun uploadFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
        galleryIntent.launch(intent)
    }
}