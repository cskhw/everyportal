package com.swengineering.everyportal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val checks: MutableLiveData<String> = MutableLiveData()
    val checksSize: MutableLiveData<Int> = MutableLiveData()
}