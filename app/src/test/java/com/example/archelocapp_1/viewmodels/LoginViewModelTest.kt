package com.example.archelocapp_1.viewmodels

import android.content.Context
import com.example.archelocapp_1.Models.LoginTableModel
import com.example.archelocapp_1.repository.LoginRepository
import com.example.archelocapp_1.repository.LoginRepositoryInterface
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*

class LoginViewModelTest {

    @Test
    fun insertData() {
        val context = mock<Context>()
        val repository = object : LoginRepositoryInterface{
            override fun insertData(context: Context, username: String, password: String) {
                assertEquals(username,"Ahla")
            }

            override fun getLoginDetails(context: Context, username: String): LoginTableModel {
                //nothing to do in this test
                return  mock()
            }
        }
        //repository.initializeDB(context)
        val loginviewmodel = LoginViewModel(context, repository=repository)
        loginviewmodel.insertData("Ahla", "Ahla123")
        //verify repository function
       // assert(repository).insertData(context, any<String>()!!, any<String>()!!)
    }
}