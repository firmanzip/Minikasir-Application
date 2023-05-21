package org.d3if3118.minikasir.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kasir")
data class MiniKasir
    (@PrimaryKey(autoGenerate = true)
                     var id: Long = 0L,
                     var tanggal: Long = System.currentTimeMillis(),
                     var namaPelanggan: String,
                     var namaBarang: String,
                     var jumlahBeli: Float,
                     var harga: Float,
                     var uangBayar: Float
)
