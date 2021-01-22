package ro.cs.pub.systems.hotel.reservations.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ro.cs.pub.systems.hotel.reservations.app.database.Reservation
import ro.cs.pub.systems.hotel.reservations.app.databinding.DayReservationsFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

class DayReservationsAdapter(private val resList: List<Reservation>, private val clickListener: (Reservation) -> Unit): RecyclerView.Adapter<DayReservationsAdapter.ReservationViewHolder>() {
    class ReservationViewHolder(private val binding: DayReservationsFragmentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(res: Reservation, clickListener: (Reservation) -> Unit){
            binding.clientName.text = res.clientName
            binding.phoneNumber.text = res.phoneNumber
            binding.checkinDate.text = this.getDate(res.checkinDate)
            binding.checkoutDate.text = this.getDate(res.checkoutDate)
            binding.otherInfo.text = res.otherInfo

            binding.cardview.setOnClickListener {
                clickListener(res)
            }
        }

        fun getDate(milliSeconds: Long): String? {
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat("dd/MM/yyyy")

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(milliSeconds)
            return formatter.format(calendar.getTime())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DayReservationsFragmentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.day_reservations_fragment, parent, false)
        return ReservationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resList.size
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(resList[position], clickListener)
    }

}