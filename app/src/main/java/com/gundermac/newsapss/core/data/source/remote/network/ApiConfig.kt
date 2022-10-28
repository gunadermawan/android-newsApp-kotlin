package com.gundermac.newsapss.core.data.source.remote.network

import com.gundermac.newsapss.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideNewsApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


//    other version to fetch api

//    private val client: Retrofit
//        get() {
//            val gson = GsonBuilder()
//                .setLenient()
//                .create()
//
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            val client: OkHttpClient = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(60, TimeUnit.MILLISECONDS)
//                .readTimeout(60, TimeUnit.MILLISECONDS)
//                .writeTimeout(60, TimeUnit.MILLISECONDS)
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build()
//        }

//    val provideApiService: ApiService get() = client.create(ApiService::class.java)