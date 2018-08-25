package com.smile.boda.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smile.boda.App
import com.smile.boda.R
import com.smile.boda.model.AirportModel
import com.smile.boda.ui.util.Util
import kotlinx.android.synthetic.main.airport_list_item.view.*

class AirportAdapter(val airports: List<AirportModel.Airport>, val selector: AirportSelector) : RecyclerView.Adapter<AirportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.airport_list_item, viewGroup, false)
        return ViewHolder(view, selector)
    }

    override fun getItemCount(): Int = airports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = airports[position]
        holder.airPortCode.text = model.cityCode
        val name = model.names["Name"]
        holder.cityName.text = Util.getTextFromAny(name)
        holder.view.tag = model
    }

    class ViewHolder(val view: View, val selector: AirportSelector) : RecyclerView.ViewHolder(view) {
        val cityName: TextView = view.cityName
        val airPortCode: TextView = view.airportCode

        init {
            view.setOnClickListener {
                selector.onAirportSelected(it.tag as AirportModel.Airport)
            }
        }
    }

    companion object {
        interface AirportSelector {
            fun onAirportSelected(airport: AirportModel.Airport)
        }
    }

}