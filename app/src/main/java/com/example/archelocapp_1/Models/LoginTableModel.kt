package com.example.archelocapp_1.Models



//first declare the class
// this class is the login table
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.archelocapp_1.utils.AuthListener

@Entity(tableName = "Login")
data class LoginTableModel(
    //this is the column is the username
    @ColumnInfo(name = "username")
    var Username: String,

    //this is the column is the user password
    @ColumnInfo(name = "password")
    var Password: String
) {

    @PrimaryKey(autoGenerate = true)//a userid is the primary key that will be automatically generated
    @ColumnInfo(name = "id")
    var Id: Int? = null

}