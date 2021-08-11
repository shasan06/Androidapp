package com.example.archelocapp_1.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archelocapp_1.Models.LoginTableModel
import com.example.archelocapp_1.repository.LoginRepository
import com.example.archelocapp_1.repository.LoginRepositoryInterface

//import kotlinx.coroutines.launch

class LoginViewModel(val context: Context, val repository: LoginRepositoryInterface) : ViewModel() {

    //var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(

        username: String,
        password: String
    ) {
        repository.insertData(context, username, password)

    }

    fun getLoginDetails(username: String) : LiveData<LoginTableModel>? {
        return MutableLiveData(repository.getLoginDetails(context, username))

    }
}

////
////
//    private val TAG = "LoginViewModel"
//    private var repository = Repository()
//    private var isLogin = MutableLiveData<Boolean>(false)
//
//
//    fun get() = isLogin
//
//    fun login(userName: String, password: String, listener: AuthListener<Boolean>) {
//        viewModelScope.launch {
//            ServiceBuilder.KEY = ""
//            val data = repository.login(userName, password)
//            if (data.isSuccessful) {
//                listener.onSuccess(true)
//                val login = data.body() as LoginTableModel
//                ServiceBuilder.KEY = "Token ${login.key}"
//            } else {
//                listener.onSuccess(false)
//            }
//
//        }
//    }
//
//
//
//}

