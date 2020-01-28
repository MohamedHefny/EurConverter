package com.mohamedhefny.eurconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mohamedhefny.eurconverter.R
import com.mohamedhefny.eurconverter.data.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainVieModel by
    lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVieModel.getEurRates()
            .observe(this, Observer {
                currencies_recycler.apply {
                    addItemDecoration(
                        DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
                    )
                    adapter = CurrenciesAdapter(it)
                }
            })
    }
}
