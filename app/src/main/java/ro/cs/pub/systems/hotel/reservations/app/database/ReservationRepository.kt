package ro.cs.pub.systems.hotel.reservations.app.database;

import androidx.lifecycle.LiveData

class ReservationRepository(val database: ReservationsDAO) {

    private suspend fun getReservationOnDay(day: Long): List<Reservation>? {
        return database.getReservationOnDay(day)
    }

    private suspend fun getAll(day: Long): LiveData<List<Reservation>>? {
        return database.getAllReservations()
    }

    private suspend fun insert(night: Reservation) {
        database.insert(night)
    }

    private suspend fun update(night: Reservation) {
        database.update(night)
    }

    suspend fun clear() {
        database.clear()
    }
}
