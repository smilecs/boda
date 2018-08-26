package com.smile.boda.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smile.boda.R
import com.smile.boda.model.Flight
import kotlinx.android.synthetic.main.schedule_item.view.*

class ScheduleAdapter(val flight: List<Flight>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.schedule_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = flight.size

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val model = flight[position]
        model.arrival.scheduledTimeLocal.dateTime

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val arrival: TextView = view.detailsArrival
        val departure: TextView = view.detailsDeparture
    }
}
