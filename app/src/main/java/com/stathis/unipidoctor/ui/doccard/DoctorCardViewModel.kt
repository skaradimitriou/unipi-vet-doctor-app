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
            val barcodeEncoder = BarcodeEncoder()

            //FIXME: Try to send a full contact to the QR parser later on
            val bitmap = barcodeEncoder.encodeBitmap(
                "https://www.google.com/search?q=stathis", BarcodeFormat.QR_CODE, 512, 512
            )
            _qr.postValue(bitmap)
        }
    }
}