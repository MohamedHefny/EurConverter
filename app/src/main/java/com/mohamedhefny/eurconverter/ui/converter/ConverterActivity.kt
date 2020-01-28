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

    /**
     * Get currency data from the coming bundle to bind it in the ui.
     * @param bundle from coming intent or saveInstanceState.
     */
    private fun extractData(bundle: Bundle) {
        currencyRate = bundle.getFloat(CURRENCY_RATE_EXTRA)
        selected_currency_rate.text = String.format("%.2f", currencyRate)
        selected_currency_name.text = bundle.getString(CURRENCY_NAME_EXTRA)!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat(CURRENCY_RATE_EXTRA, currencyRate)
        outState.putString(CURRENCY_NAME_EXTRA, selected_currency_name.text.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activty_main_enter, R.anim.activty_converter_exit)
    }
}
