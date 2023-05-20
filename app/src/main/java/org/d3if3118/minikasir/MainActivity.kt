package org.d3if3118.minikasir

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if3118.minikasir.databinding.ActivityMainBinding
import org.d3if3118.minikasir.model.Purchase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.switchDarkMode.isChecked = viewModel.isDarkMode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isDarkMode = isChecked
            viewModel.setDarkMode()
            recreate()
        }

        binding.btnReset.setOnClickListener {
            resetInput()
        }

        binding.btnProses.setOnClickListener {
            val namaPelanggan = binding.etNamaPelanggan.text.toString()

            if (!viewModel.validateInput(namaPelanggan)) {
                showToast(getString(R.string.nama_invalid))
                return@setOnClickListener
            }

            val namaBarang = binding.etNamaBarang.text.toString()
            val jumlahBeli = binding.etJumlahBeli.text.toString().toInt()
            val harga = binding.etHarga.text.toString().toInt()
            val uangBayar = binding.etUangBayar.text.toString().toInt()

            viewModel.hideKeyboard(this)

            val purchase = Purchase(namaPelanggan, namaBarang, jumlahBeli, harga, uangBayar)
            val purchaseResult = viewModel.calculatePurchaseResult(purchase)

            binding.tvTotalBelanja.text = purchaseResult.getTotalBelanjaText()
            binding.tvKeterangan.text = purchaseResult.keterangan
        }
    }

    private fun resetInput() {
        with(binding) {
            etNamaPelanggan.text?.clear()
            etNamaBarang.text?.clear()
            etJumlahBeli.text?.clear()
            etHarga.text?.clear()
            etUangBayar.text?.clear()
            tvTotalBelanja.text = ""
            tvKeterangan.text = ""
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
