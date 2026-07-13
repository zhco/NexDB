package com.nexdb.core.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexdb.NexDBApplication
import com.nexdb.core.database.dao.ConnectionDao
import com.nexdb.core.database.entity.ConnectionEntity

@Database(entities = [ConnectionEntity::class], version = 1, exportSchema = false)
abstract class NexDatabase : RoomDatabase() {

    abstract fun connectionDao(): ConnectionDao

    companion object {
        @Volatile
        private var INSTANCE: NexDatabase? = null

        fun getInstance(): NexDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    NexDBApplication.instance,
                    NexDatabase::class.java,
                    "nexdb.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
