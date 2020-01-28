package com.mohamedhefny.eurconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mohamedhefny.eurconverter.data.MainViewModel
import com.mohamedhefny.eurconverter.data.remote.ApiClient
import com.mohamedhefny.eurconverter.data.remote.ApiServices

class MainActivity : AppCompatActivity() {

    private val mainVieModel by
    lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVieModel.getEurRates()
            .observe(this, Observer {
                if (it != null)
                    Log.d("MainActivity", it.currenciesRates.toString())
            })
    }
}
