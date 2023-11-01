package com.stathis.unipidoctor.ui.calendar

import androidx.fragment.app.viewModels
import com.stathis.unipidoctor.R
import com.stathis.unipidoctor.abstraction.BaseFragment
import com.stathis.unipidoctor.databinding.FragmentCalendarBinding
import com.stathis.unipidoctor.utils.setScreenTitle


class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()

    override fun init() {
        setScreenTitle("My Calendar")
    }

    override fun startOps() {
        /*
         * FIXME : Read reference from the url bellow and adjust the calendar view
         *  https://github.com/kizitonwose/Calendar/blob/main/docs/View.md#step-1
         */
    }

    override fun stopOps() {}
}