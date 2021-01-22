package ro.cs.pub.systems.hotel.reservations.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate
import java.util.*


@Entity(tableName = "reservations")
data class Reservation (
    @PrimaryKey(autoGenerate = true)
    var reservationId: Long = 0L,

    @ColumnInfo(name = "client_name")
    var clientName: String ="",

    @ColumnInfo(name = "checkIn_date")
    var checkinDate: Long = 0L,

    @ColumnInfo(name = "checkout_date")
    var checkoutDate: Long = 0L,

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = "",

    @ColumnInfo(name = "other_info")
    var otherInfo: String = ""

)