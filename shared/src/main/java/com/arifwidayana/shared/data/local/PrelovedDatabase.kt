package com.arifwidayana.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import com.arifwidayana.shared.data.local.service.SearchDao

@Database(
    entities = [SearchHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PrelovedDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchDao
}