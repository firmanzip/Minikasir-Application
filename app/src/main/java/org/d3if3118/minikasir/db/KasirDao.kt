package org.d3if3118.minikasir.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KasirDao {
    @Insert
    fun insert(kasir: MiniKasir)

    @Query("SELECT * FROM kasir ORDER BY id DESC")
    fun getLastKasir(): LiveData<List<MiniKasir>>

    @Query("DELETE FROM kasir")
    fun clearData()
}