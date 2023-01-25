package com.arifwidayana.shared.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Query("UPDATE search_history SET updated_at= :updatedAt WHERE searchHistoryName= :searchName")
    suspend fun updateSearchHistory(searchName: String, updatedAt: Date)

    @Query("SELECT * FROM search_history")
    fun getSearchHistory(): List<SearchHistoryEntity>

    @Query("SELECT * FROM search_history WHERE searchHistoryName LIKE :searchName+'%'")
    fun getFindHistory(searchName: String): Flow<List<SearchHistoryEntity>>
}