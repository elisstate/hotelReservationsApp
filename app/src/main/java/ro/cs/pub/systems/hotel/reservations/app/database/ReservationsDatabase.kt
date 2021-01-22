package ro.cs.pub.systems.hotel.reservations.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reservation::class], version = 1, exportSchema = false)
abstract class ReservationDatabase : RoomDatabase() {

    abstract val reservationDatabaseDao: ReservationsDAO

    companion object {

        @Volatile
        private var INSTANCE: ReservationDatabase? = null

        fun getInstance(context: Context): ReservationDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReservationDatabase::class.java,
                        "reservations_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}