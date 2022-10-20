package com.example.mybonaiapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybonaiapp.model.MyRecylerList
import com.example.mybonaiapp.network.RetrofitInstance
import com.example.mybonaiapp.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel :ViewModel() {
        lateinit var recyclerListLiveData :MutableLiveData<MyRecylerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<MyRecylerList>{
        return recyclerListLiveData
    }

    fun callApi(){
        viewModelScope.launch(Dispatchers.IO) {
           val retroInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            val response =retroInstance.getDataFromApi()
            recyclerListLiveData.postValue(response)
        }
    }
}