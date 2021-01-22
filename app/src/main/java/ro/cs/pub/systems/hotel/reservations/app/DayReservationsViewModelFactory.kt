package ro.cs.pub.systems.hotel.reservations.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.cs.pub.systems.hotel.reservations.app.database.ReservationsDAO

class DayReservationsViewModelFactory(
    private val dataSource: ReservationsDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DayReservationsViewModel::class.java)) {
            return DayReservationsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}