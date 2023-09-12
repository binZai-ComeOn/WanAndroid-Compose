package com.binyouwei.wanandroid_compose.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.binyouwei.wanandroid_compose.data.db.table.SearchHistoryTable

/**
 * @author binjx
 * @date 2023/8/10 10:18
 * @purpose：
 **/
@Dao
interface SearchHistoryDao {

    @Insert
    suspend fun insertSearchHistory(vararg userInfo: SearchHistoryTable)

    @Update
    suspend fun updateSearchHistory(userInfo: SearchHistoryTable)

    @Query("SELECT * FROM search_history order by id desc limit 10")
    suspend fun querySearchHistoryList(): MutableList<SearchHistoryTable>

    @Query("SELECT * FROM search_history where name = :name")
    suspend fun querySearchHistory(name : String): MutableList<SearchHistoryTable>

    @Delete(entity = SearchHistoryTable::class)
    suspend fun deleteSearchHistory(vararg userInfo: SearchHistoryTable): Int

    @Query("DELETE FROM search_history")
    suspend fun deleteAllSearchHistory()
}