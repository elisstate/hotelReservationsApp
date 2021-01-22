package ro.cs.pub.systems.hotel.reservations.app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReservationsDAO {

    @Insert
    suspend fun insert(res: Reservation)

    @Update
    suspend fun update(night: Reservation)

    @Query("SELECT * from reservations WHERE reservationId = :key")
    suspend fun get(key: Long): Reservation?

    @Query("DELETE FROM reservations")
    suspend fun clear()

    @Query("SELECT * FROM reservations ORDER BY reservationId DESC LIMIT 1")
    suspend fun getTonight(): Reservation?

    @Query("SELECT * FROM reservations ORDER BY reservationId DESC")
    fun getAllReservations(): LiveData<List<Reservation>>

    @Query("SELECT * FROM reservations WHERE checkIn_date = :key OR checkout_date = :key")
    suspend fun getReservationOnDay(key: Long): List<Reservation>

}