package com.example.paypay.di

import android.content.Context
import com.example.paypay.DataManager
import com.example.paypay.IDataManager
import com.example.paypay.db.CurrencyDataBase
import com.example.paypay.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun providesRoomDB(
        @ApplicationContext context: Context
    ): CurrencyDataBase {
        return CurrencyDataBase.getInstance(context)
    }
    @Singleton
    @Provides
    fun providesDataManager(
        @ApplicationContext context: Context,
        dataBase: CurrencyDataBase,
        remoteRepository: RemoteRepository
    ): IDataManager {
        return DataManager(
            dataBase.currencyRateDao(),
            remoteRepository
        )
    }
}