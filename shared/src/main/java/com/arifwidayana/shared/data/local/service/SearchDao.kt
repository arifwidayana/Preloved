package com.arifwidayana.shared.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifwidayana.shared.data.local.model.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Query("UPDATE search_history SET updated_at= :updatedAt WHERE searchHistoryName= :searchName")
    suspend fun updateSearchHistory(searchName: String, updatedAt: String)

    @Query("SELECT * FROM search_history LIMIT 4")
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Query("SELECT * FROM search_history WHERE searchHistoryName LIKE :searchName||'%' LIMIT 5")
    fun getFindHistory(searchName: String): Flow<List<SearchHistoryEntity>>
}