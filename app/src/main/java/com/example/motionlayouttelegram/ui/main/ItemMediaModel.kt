package com.example.motionlayouttelegram.ui.main

import androidx.lifecycle.MutableLiveData

class ItemMediaModel {
    val mediaDescription = MutableLiveData<String>("")
    var imageUri = MutableLiveData<String>(null)
}