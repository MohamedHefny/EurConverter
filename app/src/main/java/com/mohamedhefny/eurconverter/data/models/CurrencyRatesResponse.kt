package com.mohamedhefny.eurconverter.data.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CurrencyRatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val date: String,
    @SerializedName("base") val baseCurrency: String,
    @SerializedName("rates") val currenciesRates: JsonObject
)