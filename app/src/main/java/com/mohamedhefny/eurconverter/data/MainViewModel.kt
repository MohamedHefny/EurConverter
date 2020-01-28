package com.mohamedhefny.eurconverter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blongho.country_data.World
import com.google.gson.JsonObject
import com.mohamedhefny.eurconverter.data.models.Currency
import com.mohamedhefny.eurconverter.data.models.CurrencyRatesResponse
import com.mohamedhefny.eurconverter.data.remote.ApiClient
import com.mohamedhefny.eurconverter.data.remote.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val currenciesRates = MutableLiveData<List<Currency>>()

    fun getEurRates(): LiveData<List<Currency>> {

        //TODO: Add repository to call data,
        // Implement safe api calling in a base repository.
        viewModelScope.launch(Dispatchers.IO) {

            val currencyRatesResponse: CurrencyRatesResponse =
                ApiClient.getApiClient<ApiServices>().getEurRates()

            if (currencyRatesResponse.success)
                currenciesRates.postValue(parseCurrenciesList(currencyRatesResponse.currenciesRates))
            else
                currenciesRates.postValue(mutableListOf())
        }
        return currenciesRates
    }

    /**
     * Extract currency object from list of currencies inside JSON object.
     * @param currenciesObject JSON object that contains a list of currencies and it's rates.
     * @return list of Currency object.
     * TODO: This method should be related to useCase class to improve maintainability and testing.
     */
    private fun parseCurrenciesList(currenciesObject: JsonObject): List<Currency> {
        val currenciesList: MutableList<Currency> = mutableListOf()

        currenciesObject.entrySet().iterator().forEach {
            currenciesList.add(Currency(getCurrencyFlag(it.key), it.key, it.value.asFloat))
        }

        return currenciesList
    }

    /**
     * @param currencyIsoCode is a ISO 4217 code for the currency
     * @return the corresponding currency flag.
     * TODO: This method should be related to useCase class to improve maintainability and testing.
     */
    private fun getCurrencyFlag(currencyIsoCode: String) =
        World.getFlagOf(currencyIsoCode.substring(0, 2))
}