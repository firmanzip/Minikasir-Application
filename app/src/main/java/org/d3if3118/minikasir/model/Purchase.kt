package org.d3if3118.minikasir.model

data class Purchase(
    val namaPelanggan: String,
    val namaBarang: String,
    val jumlahBeli: Int,
    val harga: Int,
    val uangBayar: Int
)

