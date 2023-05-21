package org.d3if3118.minikasir.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MiniKasir::class], version = 1, exportSchema = false)
abstract class KasirDb : RoomDatabase() {
    abstract val dao: KasirDao

    companion object {
        @Volatile
        private var INSTANCE: KasirDb? = null
        fun getInstance(context: Context): KasirDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KasirDb::class.java,
                        "kasir.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
