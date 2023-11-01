package com.stathis.unipidoctor.ui.changeaddress

import android.app.Application
import com.stathis.unipidoctor.abstraction.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}