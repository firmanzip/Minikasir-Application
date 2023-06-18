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
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.postValue(TokoApi.service.getToko())
            } catch (e: Exception) {
                Log.d("TokoViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Toko>> = data
}
