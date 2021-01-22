package ro.cs.pub.systems.hotel.reservations.app

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ro.cs.pub.systems.hotel.reservations.app.databinding.DayReservationsFragmentBinding

class DayReservations : Fragment() {

    companion object {
        fun newInstance() = DayReservations()
    }

    private lateinit var viewModel: DayReservationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DayReservationsFragmentBinding>(inflater,
            R.layout.day_reservations_fragment,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayReservationsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}