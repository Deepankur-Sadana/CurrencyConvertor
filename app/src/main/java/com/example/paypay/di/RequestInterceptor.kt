package com.example.paypay.di

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest = chain.request()
//        val requestBuilder = originalRequest.newBuilder()
//            .header("Authorization", "AuthToken")
//        val request = requestBuilder.build()

//        return chain.proceed(request)

        var request = chain.request();
        val url = request.url
            .newBuilder()
            .addQueryParameter("app_id", "f0223b66dbd7460fb41437707310cdbd")
            .build();

        request = request
            .newBuilder()
            .addHeader("accept", "application/json")
            .url(url)
            .build();

        return chain.proceed(request)
    }
}

//Request request = chain.request();
//HttpUrl url = request.url().newBuilder().addQueryParameter("name","value").build();
//request = request.newBuilder().url(url).build();
//return chain.proceed(request);