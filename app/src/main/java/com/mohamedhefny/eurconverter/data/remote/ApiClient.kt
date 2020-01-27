package com.mohamedhefny.eurconverter.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mohamedhefny.eurconverter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    /**
     * @return ApiClient for any T service class.
     **/
    inline fun <reified T> getApiClient(): T = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build().create(T::class.java)
}