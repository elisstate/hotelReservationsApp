package ro.cs.pub.systems.hotel.reservations.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import ro.cs.pub.systems.hotel.reservations.app.database.ReservationDatabase
import ro.cs.pub.systems.hotel.reservations.app.databinding.FragmentNewReservationFormBinding

class NewReservationFormFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNewReservationFormBinding>(
            inflater,
            R.layout.fragment_new_reservation_form, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = ReservationDatabase.getInstance(application).reservationDatabaseDao
        val viewModelFactory = DayReservationsViewModelFactory(dataSource, application)
        val reservationTrackerViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DayReservationsViewModel::class.java)
        binding.reservationViewModel = reservationTrackerViewModel

        binding.closeNewReservation.setOnClickListener { view ->
            this.dismiss()
        }
        return binding.root
    }

    companion object {
        const val TAG = "NEW_RESERVATION_DIALOG"
    }
}