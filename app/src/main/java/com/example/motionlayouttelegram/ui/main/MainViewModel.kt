package com.example.motionlayouttelegram.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val name = MutableLiveData<String>("Rainbow")
    var status = MutableLiveData<String>("last seen recently")
    var imgUrl = MutableLiveData<String>()
}