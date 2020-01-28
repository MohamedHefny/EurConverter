package com.mohamedhefny.eurconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.blongho.country_data.World
import com.mohamedhefny.eurconverter.R
import com.mohamedhefny.eurconverter.data.MainViewModel
import com.mohamedhefny.eurconverter.data.models.Currency
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CurrenciesAdapter.CurrencyCallback {

    private val mainVieModel by
    lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        World.init(applicationContext)

        mainVieModel.getEurRates()
            .observe(this, Observer {
                currencies_recycler.apply {
                    addItemDecoration(
                        DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
                    )
                    adapter = CurrenciesAdapter(it, this@MainActivity)
                }
            })
    }

    override fun onCurrencyClicked(currency: Currency) {

    }
}
