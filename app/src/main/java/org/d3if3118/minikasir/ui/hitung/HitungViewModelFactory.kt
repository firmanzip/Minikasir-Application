package org.d3if3118.minikasir.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3118.minikasir.db.KasirDao

class HitungViewModelFactory(
    private val db: KasirDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}