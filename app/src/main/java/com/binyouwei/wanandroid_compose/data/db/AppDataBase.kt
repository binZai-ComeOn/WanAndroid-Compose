package com.binyouwei.wanandroid_compose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import com.binyouwei.wanandroid_compose.data.db.dao.SearchHistoryDao
import com.binyouwei.wanandroid_compose.data.db.table.SearchHistoryTable

/**
 * @author binjx
 * @date 2023/8/10 10:03
 * @purpose：
 **/
@Database(
    entities = [SearchHistoryTable::class],
    version = AppConstant.dbVersion
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}