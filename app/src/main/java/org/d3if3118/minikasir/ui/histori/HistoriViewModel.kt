package org.d3if3118.minikasir.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3118.minikasir.db.KasirDao
import org.d3if3118.minikasir.db.MiniKasir

class HistoriViewModel(private val db: KasirDao) : ViewModel() {
    val data = db.getLastKasir()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}