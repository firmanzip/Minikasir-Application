package org.d3if3118.minikasir

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import org.d3if3118.minikasir.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.switchDarkMode.isChecked = true
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }
        binding.btnReset.setOnClickListener {
            resetInput()
        }
        // Add click listener to the "Proses" button
        binding.btnProses.setOnClickListener {
            // Get input values from the EditText views
            val namaPelanggan = binding.etNamaPelanggan.text.toString()
            val namaBarang = binding.etNamaBarang.text.toString()
            val jumlahBeli = binding.etJumlahBeli.text.toString().toInt()
            val harga = binding.etHarga.text.toString().toInt()
            val uangBayar = binding.etUangBayar.text.toString().toInt()
            // inisialisasi objek InputMethodManager

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            // Calculate total belanja
            val totalBelanja = jumlahBeli * harga

            // Calculate uang kembali
            val uangKembali = uangBayar - totalBelanja

            // Calculate bonus
            val bonus = if (uangBayar >= 50000) "Bonus 1 barang" else "Tidak ada Bonus"

            // Set the text for the result TextView
            val resultText = "Total belanja: Rp $totalBelanja\n" +
                    "Uang kembali: Rp $uangKembali\n" +
                    "Bonus: $bonus"

            binding.tvTotalBelanja.text = resultText

            // Set the text keterangan TextView
            val keterangan = if (uangKembali >= 0) "Terima kasih telah berbelanja." else "Uang bayar tidak mencukupi."
            binding.tvKeterangan.text = keterangan
        }
    }

    private fun resetInput() {
        binding.etNamaPelanggan.setText("")
        binding.tvTotalBelanja.setText("")
        binding.etJumlahBeli.setText("")
        binding.etNamaBarang.setText("")
        binding.tvKeterangan.setText("")
        binding.etHarga.setText("")
        binding.etUangBayar.setText("")
    }
}
