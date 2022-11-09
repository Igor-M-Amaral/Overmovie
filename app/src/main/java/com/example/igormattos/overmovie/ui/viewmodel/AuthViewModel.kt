package com.example.igormattos.overmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.overmovie.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _verify = MutableLiveData<Boolean>()
    val verify: LiveData<Boolean> = _verify

    fun register(email: String, userName: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            _verify.postValue(repository.register(email, userName, password))
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            _verify.postValue(repository.login(email, password))
        }
    }

    fun forgetPassword(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            _verify.postValue(repository.forgetPassword(email))
        }
    }
}