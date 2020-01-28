package com.mohamedhefny.eurconverter.data.remote

import com.mohamedhefny.eurconverter.BuildConfig
import com.mohamedhefny.eurconverter.data.models.CurrencyRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("latest")
    suspend fun getEurRates(
        @Query("format") format: Int = 1,
        @Query("access_key") access_key: String = BuildConfig.API_KEY
    ): CurrencyRatesResponse
}