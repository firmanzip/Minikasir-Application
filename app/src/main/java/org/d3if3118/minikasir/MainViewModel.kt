package org.d3if3118.minikasir

import android.content.Context
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import org.d3if3118.minikasir.R
import org.d3if3118.minikasir.model.BonusType
import org.d3if3118.minikasir.model.Purchase
import org.d3if3118.minikasir.model.PurchaseResult

class MainViewModel : ViewModel() {
    var isDarkMode: Boolean = false

    fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun validateInput(namaPelanggan: String): Boolean {
        return !TextUtils.isEmpty(namaPelanggan)
    }

    fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(null, 0)
    }

    fun calculatePurchaseResult(purchase: Purchase): PurchaseResult {
        val totalBelanja = purchase.jumlahBeli * purchase.harga
        val uangKembali = purchase.uangBayar - totalBelanja
        val bonusType = BonusType.getBonusTypeByPurchase(totalBelanja)
        val keterangan = if (uangKembali >= 0) "Terima kasih telah berbelanja." else "Uang anda tidak mencukupi."

        return PurchaseResult(totalBelanja, uangKembali, bonusType, keterangan)
    }
}
