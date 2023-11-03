package com.stathis.unipidoctor.ui.analytics

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.BarEntry
import com.stathis.unipidoctor.abstraction.BaseViewModel
import com.stathis.unipidoctor.ui.analytics.uimodel.AnalyticsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    val data: LiveData<List<AnalyticsModel>>
        get() = _data

    private val _data: MutableLiveData<List<AnalyticsModel>> = MutableLiveData(listOf())

    val dataset: LiveData<List<BarEntry>>
        get() = _dataset

    private val _dataset: MutableLiveData<List<BarEntry>> = MutableLiveData(listOf())

    fun populateChart() {
        val list = mutableListOf<AnalyticsModel>()
        val bars = mutableListOf<BarEntry>()

        repeat(10) {
            val month = Month.of(it + 1)
            val monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
            val value = Random.Default.nextInt(10, 50).toString()
            list.add(
                AnalyticsModel(
                    title = monthName,
                    description = value
                )
            )
            bars.add(BarEntry((it + 1).toFloat(), value.toFloat()))
        }

        _dataset.postValue(bars)
        _data.postValue(list)
    }
}
