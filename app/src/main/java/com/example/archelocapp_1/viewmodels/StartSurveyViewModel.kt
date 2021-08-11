package com.example.archelocapp_1.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.repository.MorningSurveyInterface
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient

class StartSurveyViewModel(val context: Context, val surveyrepo: MorningSurveyInterface) : ViewModel() {

    //declaring the object
    var fusedLocationClient: FusedLocationProviderClient? = null
    var morningSurveyModel = MorningSurvey()//object for morning survey model
    var db: MorningSurveyDatabase? = null  //room database
    var morningSurveyDao: MorningSurveyDatabaseDAO? = null //interface


    // Creating an instance of the database if it does not already exist

    fun loadDb( context: Context) {
        if (db == null) {
            db = Room.databaseBuilder(context,MorningSurveyDatabase::class.java,"Morning_Survey_DB")
                .build()
        }
        morningSurveyDao = db!!.morningSurveyDatabaseDAO()

    }

    // get the latest available location to be recorded for morning survey(used in start survey fragment)

    fun loadLocationProvider(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    //function for inserting the data into database
    fun insert(
        morningSurvey: MorningSurvey,
    ) {
        surveyrepo.insert(context, morningSurvey)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("StartSurvey", "StartSurveyFragment destroyed!")
    }
}