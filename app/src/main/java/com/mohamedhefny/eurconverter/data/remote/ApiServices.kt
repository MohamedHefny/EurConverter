package com.mohamedhefny.eurconverter.data.remote

import com.mohamedhefny.eurconverter.BuildConfig
import com.mohamedhefny.eurconverter.data.models.CurrencyRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    /**
     * Get the list of available currencies rates from fixer.io api.
     * @param format (Optional) of the response, set to 1 for readable JSON response.
     * @param baseCurrency (Optional) you want to get rates for.
     * @param access_key your fixer.io API key (Optional but must have a valid value).
     * @return a CurrencyRatesResponse object.
     */
    @GET("latest")
    suspend fun getEurRates(
        @Query("format") format: Int = 1,
        @Query("base") baseCurrency: String = "eur",
        @Query("access_key") access_key: String = BuildConfig.API_KEY
    ): CurrencyRatesResponse
}