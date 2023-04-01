package org.d3if3118.minikasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.d3if3118.minikasir.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add click listener to the "Proses" button
        binding.btnProses.setOnClickListener {
            // Get input values from the EditText views
            val namaPelanggan = binding.etNamaPelanggan.text.toString()
            val namaBarang = binding.etNamaBarang.text.toString()
            val jumlahBeli = binding.etJumlahBeli.text.toString().toInt()
            val harga = binding.etHarga.text.toString().toInt()
            val uangBayar = binding.etUangBayar.text.toString().toInt()

        }
    }
}
