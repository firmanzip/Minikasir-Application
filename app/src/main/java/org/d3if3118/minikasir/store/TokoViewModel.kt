package org.d3if3118.minikasir.store

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3118.minikasir.internet.TokoApi
import org.d3if3118.minikasir.internet.UpdateWorker
import org.d3if3118.minikasir.model.Toko
import java.util.concurrent.TimeUnit


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
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
