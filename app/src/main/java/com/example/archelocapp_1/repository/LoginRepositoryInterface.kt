package com.example.archelocapp_1.repository
//helps in testing the functions ed repository inside a repository
import android.content.Context
import com.example.archelocapp_1.Models.LoginTableModel

interface LoginRepositoryInterface {

    fun insertData(context: Context, username: String, password: String)

    fun getLoginDetails(context: Context, username: String) : LoginTableModel

}