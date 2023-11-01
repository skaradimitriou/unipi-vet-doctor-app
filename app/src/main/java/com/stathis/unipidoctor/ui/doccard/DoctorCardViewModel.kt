package com.stathis.unipidoctor.ui.doccard

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.di.IoDispatcher
import com.stathis.unipidoctor.ui.doccard.uimodel.DoctorCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DoctorCardViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val doctorCard: LiveData<DoctorCard>
        get() = _doctorCard

    private val _doctorCard = MutableLiveData<DoctorCard>()

    val qr: LiveData<Bitmap>
        get() = _qr

    private val _qr = MutableLiveData<Bitmap>()

    fun getDoctorInfo() {
        viewModelScope.launch(dispatcher) {
            val dummyDoctorCard = DoctorCard("", "Doctor Doctoropoulos")
            _doctorCard.postValue(dummyDoctorCard)
        }
    }

    fun generateQR() {
        viewModelScope.launch(dispatcher) {
            //FIXME: Replace fullname & phone number & email with doctor's values
            val doctorContact = "BEGIN:VCARD\n" +
                    "VERSION:3.0\n" +
                    "FN:John Doe\n" + // Full name
                    "TEL:1234567890\n" + // Phone number
                    "EMAIL:john.doe@example.com\n" + // Email address
                    // Add more fields as needed
                    "END:VCARD"

            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(
                doctorContact, BarcodeFormat.QR_CODE, 512, 512
            )
            _qr.postValue(bitmap)
        }
    }
}