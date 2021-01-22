package ro.cs.pub.systems.hotel.reservations.app

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import ro.cs.pub.systems.hotel.reservations.app.database.Reservation
import ro.cs.pub.systems.hotel.reservations.app.database.ReservationDatabase
import ro.cs.pub.systems.hotel.reservations.app.databinding.FragmentCalendarReservationsBinding
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarReservations.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarReservations : Fragment() {
    private var calendar: Calendar = Calendar.getInstance();
    private val dialog: ReservationsOnDay = ReservationsOnDay()
    private val newReservationDialog: NewReservationFormFragment = NewReservationFormFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCalendarReservationsBinding>(inflater,
            R.layout.fragment_calendar_reservations,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = ReservationDatabase.getInstance(application).reservationDatabaseDao
        val viewModelFactory = DayReservationsViewModelFactory(dataSource, application)
        val reservationTrackerViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DayReservationsViewModel::class.java)


        binding.reservationTrackerViewModel = reservationTrackerViewModel

        binding.addReservation.setOnClickListener{ _: View ->
            newReservationDialog.show(childFragmentManager, NewReservationFormFragment.TAG)
        }


        binding.calendarView.setOnDayClickListener {eventDay: EventDay ->
            dialog.show(childFragmentManager, ReservationsOnDay.TAG)
            var toadd: MutableList<EventDay> = mutableListOf<EventDay>()

            toadd.add(eventDay)
            binding.calendarView.setEvents(toadd)

        }


        return binding.root
    }
}