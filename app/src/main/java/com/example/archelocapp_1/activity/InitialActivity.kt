package com.example.archelocapp_1.activity

import android.Manifest.permission
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.archelocapp_1.R
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class InitialActivity : AppCompatActivity(R.layout.activity_initial){
    private  val TAG = "InitialActivity"
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var db: MorningSurveyDatabase
    private lateinit var morningDao: MorningSurveyDatabaseDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//check
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        db = Room.inMemoryDatabaseBuilder(this, MorningSurveyDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        morningDao = db.morningSurveyDatabaseDAO()


        ActivityCompat.requestPermissions(this, arrayOf(
                permission.CAMERA,
                permission.READ_EXTERNAL_STORAGE,
                permission.ACCESS_FINE_LOCATION,
                permission.ACCESS_COARSE_LOCATION
            ), 100)

//        findViewById<AppCompatButton>(R.id.arrowBtn).setOnClickListener {
//            Log.e(TAG, "onCreate:")
//        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        Log.d(TAG, "onBackPressed: " + findNavController(R.id.fragment).currentDestination?.id)




    }



}

