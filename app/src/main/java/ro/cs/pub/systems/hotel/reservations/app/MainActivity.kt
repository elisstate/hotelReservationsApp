package ro.cs.pub.systems.hotel.reservations.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import ro.cs.pub.systems.hotel.reservations.app.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var nav: NavController
        setContentView(R.layout.activity_main)
    }

}