package com.example.archelocapp_1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.archelocapp_1.Models.LoginTableModel
import com.example.archelocapp_1.room.LoginDatabase
//import com.example.room.archelocapp_1.Models.LoginTableModel
//import com.example.room.archelocapp_1.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

open class LoginRepository(val loginDatabase: LoginDatabase): LoginRepositoryInterface {

    //companion object {

        //lateinit var loginDatabase: LoginDatabase

        //lateinit var loginTableModel: LoginTableModel

//        fun initializeDB(context: Context)  {
//            loginDatabase = LoginDatabase.getDataseClient(context)
//        }


        override fun insertData(context: Context, username: String, password: String) {

            //initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(username, password)
                loginDatabase.loginDao().InsertData(loginDetails)
            }

        }

        override fun getLoginDetails(context: Context, username: String) : LoginTableModel {

            //initializeDB(context)
            var loginTableModel: LoginTableModel?=null

            CoroutineScope(IO).launch {
                loginTableModel = loginDatabase.loginDao().getLoginDetails(username)
            }
            return loginTableModel!!
        }

    //}
}