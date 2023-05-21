package org.d3if3118.minikasir.model

data class Purchase(
    val namaPelanggan: String,
    val namaBarang: String,
    val jumlahBeli: Float,
    val harga: Float,
    val uangBayar: Float
)

