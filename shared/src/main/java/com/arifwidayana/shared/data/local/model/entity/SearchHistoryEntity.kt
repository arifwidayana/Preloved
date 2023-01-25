package com.arifwidayana.shared.data.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey
    val searchHistoryName: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Date?,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date?
)
