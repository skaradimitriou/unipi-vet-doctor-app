package com.stathis.unipidoctor.ui.calendar

import androidx.fragment.app.viewModels
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentCalendarBinding
import com.stathis.unipidoctor.ui.calendar.adapter.AppointmentsAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDateTime
import java.time.Month
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()

    private val adapter = AppointmentsAdapter()

    override fun init() {
        setScreenTitle("My Calendar")

        binding.appointmentsRecycler.apply {
            removeItemDecorations()
            adapter = this@CalendarFragment.adapter
            addItemDecoration(VerticalItemDecoration(30))
        }

        viewModel.fetchDoctorAppointments()
    }

    override fun startOps() {
        val currentMonth = LocalDateTime.now().month.toString()
        binding.monthTxtView.text = currentMonth

        binding.calendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date?) {
                viewModel.getAppointmentsForDate(dateClicked ?: Date())
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                val month = Month.of((firstDayOfNewMonth?.month?.plus(1) ?: 0))
                val monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH).uppercase()
                binding.monthTxtView.text = monthName
            }
        })

        viewModel.appointments.observe(viewLifecycleOwner) { appointments ->
            adapter.submitList(appointments)
        }
    }

    override fun stopOps() {}
}