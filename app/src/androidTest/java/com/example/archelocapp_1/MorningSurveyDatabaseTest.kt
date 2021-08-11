package com.example.archelocapp_1

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.archelocapp_1.Models.MorningSurvey
import com.example.archelocapp_1.room.MorningSurveyDatabase
import com.example.archelocapp_1.room.MorningSurveyDatabaseDAO
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
class MorningSurveyDatabaseTest {

    private lateinit var morningSurveyDao: MorningSurveyDatabaseDAO
    private lateinit var db: MorningSurveyDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //Using an in-memory database because the information stored here disappears when the
        //process is killed
        db = Room.inMemoryDatabaseBuilder(context, MorningSurveyDatabase::class.java)
            .allowMainThreadQueries()
            .build()//allowing the main thread queries just for testing
        morningSurveyDao = db.morningSurveyDatabaseDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert(){
        runBlocking { val morningSurvey = MorningSurvey()
            morningSurveyDao.insert(morningSurvey)
            val morningSurveydao = morningSurveyDao.getrecentSurvey()
            Assert.assertEquals(morningSurveydao?.non_nesting_attempts, "-1")
            }
    }
}


