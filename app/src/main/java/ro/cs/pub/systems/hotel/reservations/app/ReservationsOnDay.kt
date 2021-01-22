package ro.cs.pub.systems.hotel.reservations.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ro.cs.pub.systems.hotel.reservations.app.database.Reservation
import ro.cs.pub.systems.hotel.reservations.app.database.ReservationDatabase
import ro.cs.pub.systems.hotel.reservations.app.databinding.FragmentReservationsOnDayBinding

class ReservationsOnDay : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentReservationsOnDayBinding>(inflater,
            R.layout.fragment_reservations_on_day,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = ReservationDatabase.getInstance(application).reservationDatabaseDao
        val viewModelFactory = DayReservationsViewModelFactory(dataSource, application)
        val reservationTrackerViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DayReservationsViewModel::class.java)


        binding.listReservations.layoutManager = LinearLayoutManager(this.activity)
        binding.lifecycleOwner = this

        reservationTrackerViewModel.reservations.observe(this, Observer {
            Log.i("Main", it.toString())
            binding.listReservations.adapter = DayReservationsAdapter(it) { selectedItem: Reservation -> listItemClicked(selectedItem)}
        })
        return binding.root
    }

    private fun listItemClicked(res: Reservation){
        Toast.makeText(this.activity, "Reservation for client ${res.clientName} clicked", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "RESERV_DIALOG"
    }
}