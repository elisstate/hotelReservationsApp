package ro.cs.pub.systems.hotel.reservations.app

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.cs.pub.systems.hotel.reservations.app.database.Reservation
import ro.cs.pub.systems.hotel.reservations.app.database.ReservationsDAO

class DayReservationsViewModel(val database: ReservationsDAO, application: Application) :
    AndroidViewModel(application), Observable  {
    // TODO: Implement the ViewModel
    val reservations = database.getAllReservations()
    @Bindable
    public val clientName = MutableLiveData<String>()
    @Bindable
    public val phoneNumber = MutableLiveData<String>()
    @Bindable
    public  val checking = System.currentTimeMillis()
    @Bindable
    public val checkoutDate = System. currentTimeMillis()
    @Bindable
    public val otherInfo = MutableLiveData<String>()

    /**
     * Binding the buttons to change the text dynamically
     */
    @Bindable
    val saveOrUpdateBtn = MutableLiveData<String>()
    init {

    }

    private suspend fun getReservationOnDay(day: Long): List<Reservation>? {
        return database.getReservationOnDay(day)
    }

    fun save(){
        val name: String = clientName.value!!
        val phoneNumber = phoneNumber.value!!
        val indate = checking
        val checkout = checkoutDate
        val otherInfo = otherInfo.value!!
        insertIntoDb(name, phoneNumber, System. currentTimeMillis(), System. currentTimeMillis(), otherInfo)
        this.clientName.value = null
        this.otherInfo.value = null
    }

    fun insertIntoDb(
        clientName: String, phoneNumber: String,
        checking: Long, checkout: Long, otherInfo: String
    ) {
        viewModelScope.launch {
            val res = Reservation(0,
                clientName,
                checking,
                checkout,
                phoneNumber,
                otherInfo
            )
            insert(res)
        }
    }

    fun populateDb() {
        insertIntoDb("Mark Thomson", "0943561314",System. currentTimeMillis(), System. currentTimeMillis(),
            "Seaside room");
        insertIntoDb("Helen Jackson", "543135725",System. currentTimeMillis(), System. currentTimeMillis(),
            "Room which allows pets");
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

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}