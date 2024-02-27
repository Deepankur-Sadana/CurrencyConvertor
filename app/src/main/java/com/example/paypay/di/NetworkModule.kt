package com.example.paypay.di

import com.example.paypay.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val BASE_URL = "https://openexchangerates.org/api/"
    }
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(RequestInterceptor())
            .addNetworkInterceptor(loggingInterceptor)
            .build();
        return okHttpClient
    }


    @Provides
    @Singleton
    fun providesCurrencyApi(
        retrofit: Retrofit
    ): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }
}

