package com.example.btk3_besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.btk3_besinlerkitabi.model.Besin
import com.example.btk3_besinlerkitabi.service.BesinDatabase
import kotlinx.coroutines.launch
import java.util.UUID

class BesinDetayiViewModel(application: Application) : BaseViewModel(application){
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid: Int){
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }

}