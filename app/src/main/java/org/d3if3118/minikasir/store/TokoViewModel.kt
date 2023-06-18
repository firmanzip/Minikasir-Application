package org.d3if3118.minikasir.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3118.minikasir.internet.TokoApi
import org.d3if3118.minikasir.model.Toko


class TokoViewModel : ViewModel() {
    private val data = MutableLiveData<List<Toko>>()
    private val status = MutableLiveData<TokoApi.ApiStatus>()
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(TokoApi.ApiStatus.LOADING)
            try {
                data.postValue(TokoApi.service.getToko())
                status.postValue(TokoApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("TokoViewModel", "Failure: ${e.message}")
                status.postValue(TokoApi.ApiStatus.FAILED)
            }

        }
    }

    fun getData(): LiveData<List<Toko>> = data
    fun getStatus(): LiveData<TokoApi.ApiStatus> = status
}
