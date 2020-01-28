package com.mohamedhefny.eurconverter.ui.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.mohamedhefny.eurconverter.R
import com.mohamedhefny.eurconverter.utils.CURRENCY_BUNDLE
import com.mohamedhefny.eurconverter.utils.CURRENCY_NAME_EXTRA
import com.mohamedhefny.eurconverter.utils.CURRENCY_RATE_EXTRA
import kotlinx.android.synthetic.main.activity_converter.*

class ConverterActivity : AppCompatActivity() {

    private var currencyRate: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        if (savedInstanceState == null)
            extractData(intent.getBundleExtra(CURRENCY_BUNDLE)!!)
        else extractData(savedInstanceState)

        base_currency_rate.addTextChangedListener {
            if (it.isNullOrEmpty())
                selected_currency_rate.text = null
            else
                selected_currency_rate.text = String
                    .format("%.2f", it.toString().toFloat().times(currencyRate))
        }
    }

    private fun extractData(bundle: Bundle) {
        selected_currency_name.text = bundle.getString(CURRENCY_NAME_EXTRA)!!
        currencyRate = bundle.getFloat(CURRENCY_RATE_EXTRA)
        selected_currency_rate.text = String.format("%.2f", currencyRate)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activty_main_enter, R.anim.activty_converter_exit)
    }
}
