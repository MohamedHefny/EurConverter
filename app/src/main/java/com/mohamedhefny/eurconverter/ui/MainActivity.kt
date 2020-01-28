package com.mohamedhefny.eurconverter.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.mohamedhefny.eurconverter.R
import com.mohamedhefny.eurconverter.data.MainViewModel
import com.mohamedhefny.eurconverter.data.models.Currency
import com.mohamedhefny.eurconverter.ui.converter.ConverterActivity
import com.mohamedhefny.eurconverter.utils.CURRENCY_BUNDLE
import com.mohamedhefny.eurconverter.utils.CURRENCY_NAME_EXTRA
import com.mohamedhefny.eurconverter.utils.CURRENCY_RATE_EXTRA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CurrenciesAdapter.CurrencyCallback {

    private val mainVieModel by
    lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        World.init(applicationContext)

        //Observe currencies rate and update ui when rates are available.
        mainVieModel.getEurRates().observe(this, Observer {
            main_loading.visibility = View.GONE
            currencies_recycler.apply {
                addItemDecoration(
                    DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
                )
                adapter = CurrenciesAdapter(it, this@MainActivity)
            }
        })
    }

    override fun onCurrencyClicked(currency: Currency) {
        val currencyData: Bundle = Bundle().apply {
            putString(CURRENCY_NAME_EXTRA, currency.name)
            putFloat(CURRENCY_RATE_EXTRA, currency.rate)
        }

        Intent(this, ConverterActivity::class.java).apply {
            putExtra(CURRENCY_BUNDLE, currencyData)
            startActivity(this)
        }

        overridePendingTransition(R.anim.activty_converter_enter, R.anim.activty_main_exit)
    }
}
