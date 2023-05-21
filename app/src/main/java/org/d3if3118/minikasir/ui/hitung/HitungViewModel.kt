package org.d3if3118.minikasir.ui.hitung

import android.content.Context
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3118.minikasir.db.KasirDao
import org.d3if3118.minikasir.db.MiniKasir
import org.d3if3118.minikasir.model.BonusType
import org.d3if3118.minikasir.model.Purchase
import org.d3if3118.minikasir.model.PurchaseResult
import org.d3if3118.minikasir.model.hitungTotal

class HitungViewModel(private val db: KasirDao) : ViewModel() {
    private val purchaseResult = MutableLiveData<PurchaseResult?>()

    var isDarkMode: Boolean = false

    fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun hitungTotal(namaPelanggan: String, namaBarang: String, jumlahBeli: Float, harga: Float, uangBayar: Float) {
        val data = MiniKasir(
            namaPelanggan = namaPelanggan,
            namaBarang = namaBarang,
            jumlahBeli = jumlahBeli,
            harga = harga,
            uangBayar = uangBayar
        )
        purchaseResult.value = data.hitungTotal()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(data)
            }
        }
    }
    fun getHasil():LiveData<PurchaseResult?> = purchaseResult

}



