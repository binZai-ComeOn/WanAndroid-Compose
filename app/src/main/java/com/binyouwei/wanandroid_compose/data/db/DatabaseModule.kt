package com.binyouwei.wanandroid_compose.data.db

import androidx.room.Room
import com.binyouwei.common.helper.AppHelper
import com.binyouwei.wanandroid_compose.data.constant.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author binjx
 * @date 2023/8/10 11:09
 * @purpose：
 **/
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideSearchHistory(): AppDataBase {
        return Room.databaseBuilder(AppHelper.getApplication(), AppDataBase::class.java, AppConstant.dbName)
            .build()
    }

}