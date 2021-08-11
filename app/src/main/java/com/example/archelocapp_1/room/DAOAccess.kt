package com.example.archelocapp_1.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.archelocapp_1.Models.LoginTableModel

//interface is the abstraction to hide the implementation of these functions in the class
@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    suspend fun getLoginDetails(username: String) : LoginTableModel





}