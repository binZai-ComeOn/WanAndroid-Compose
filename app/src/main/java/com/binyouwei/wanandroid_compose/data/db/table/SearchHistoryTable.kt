package com.binyouwei.wanandroid_compose.data.db.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import kotlinx.parcelize.Parcelize

/**
 * @author binjx
 * @date 2023/8/10 10:06
 * @purpose：
 **/
@Entity(tableName = AppConstant.tableSearchHistory)
@Parcelize
data class SearchHistoryTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo var name: String,
    @ColumnInfo var time: Long,
) : Parcelable