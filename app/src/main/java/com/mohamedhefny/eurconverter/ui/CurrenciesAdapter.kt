package com.mohamedhefny.eurconverter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohamedhefny.eurconverter.R
import com.mohamedhefny.eurconverter.data.models.Currency
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrenciesAdapter(private val currencies: List<Currency>) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val currencyView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(currencyView)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindCurrencyData(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currencyImage: ImageView = itemView.item_currency_img
        private val currencyName: TextView = itemView.item_currency_name
        private val currencyRate: TextView = itemView.item_currency_rate

        fun bindCurrencyData(currency: Currency) {
            currencyName.text = currency.name
            currencyRate.text = String.format("%.2f", currency.rate)
            currencyImage.setImageResource(currency.image)
        }
    }
}