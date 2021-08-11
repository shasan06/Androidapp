package com.example.archelocapp_1

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.archelocapp_1.room.LoginDatabase//as Login database is in the room package
import com.example.archelocapp_1.room.DAOAccess
import com.example.archelocapp_1.Models.LoginTableModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
class LoginDatabaseTest {

    private lateinit var loginDao: DAOAccess
    private lateinit var db: LoginDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //Using an in-memory database because the information stored here disappears when
        //the process is killed
        db = Room.inMemoryDatabaseBuilder(context, LoginDatabase::class.java)
                 .allowMainThreadQueries()
                  .build()//allowing the main thread queries just for testing

        loginDao = db.loginDao()//eq 1
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insert() {
        runBlocking { val login = LoginTableModel("Ahla", "Ahla123")
            //calling the interface object that created in eq 1 to insert the login credentials
            loginDao.InsertData(login)

            //get login details
            val logindetails =loginDao.getLoginDetails("Ahla")
            assertEquals(logindetails.Username, "Ahla") }
        //moq object for loginTableModel


    }
}

