package com.mohamedhefny.eurconverter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedhefny.eurconverter.data.models.CurrencyRatesResponse
import com.mohamedhefny.eurconverter.data.remote.ApiClient
import com.mohamedhefny.eurconverter.data.remote.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val currenciesRates = MutableLiveData<CurrencyRatesResponse?>()

    fun getEurRates(): LiveData<CurrencyRatesResponse?> {

        //TODO: Add repository to call data,
        // Implement safe api calling in a base repository.
        viewModelScope.launch(Dispatchers.IO) {

            val currencyRatesResponse: CurrencyRatesResponse =
                ApiClient.getApiClient<ApiServices>().getEurRates()

            if (currencyRatesResponse.success)
                currenciesRates.postValue(currencyRatesResponse)
            else
                currenciesRates.postValue(null)
        }
        return currenciesRates
    }
}