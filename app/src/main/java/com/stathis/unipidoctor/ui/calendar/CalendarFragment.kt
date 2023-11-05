package com.stathis.unipidoctor.ui.calendar

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.stathis.domain.model.ChatConversation
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentCalendarBinding
import com.stathis.unipidoctor.navigation.NavigationAction
import com.stathis.unipidoctor.ui.MainSharedViewModel
import com.stathis.unipidoctor.ui.calendar.adapter.AppointmentsAdapter
import com.stathis.unipidoctor.utils.decor.VerticalItemDecoration
import com.stathis.unipidoctor.utils.removeItemDecorations
import com.stathis.unipidoctor.utils.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.Month
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()
    private val sharedVM: MainSharedViewModel by activityViewModels()

    private val adapter = AppointmentsAdapter { appointment ->
        val conversation = ChatConversation(appointment.patientImage, appointment.patientName)
        val args = Bundle().apply {
            putParcelable("CONVERSATION", conversation)
        }
        sharedVM.navigateToScreen(NavigationAction.CHAT_CONVERSATION, args = args)
    }

    override fun init() {
        setScreenTitle(getString(R.string.my_calendar))
        binding.emptyAppointments = false

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
            binding.emptyAppointments = appointments.isEmpty()
            adapter.submitList(appointments)
        }
    }

    override fun stopOps() {}
}