package com.smile.boda.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smile.boda.R
import com.smile.boda.model.Flight
import com.smile.boda.ui.util.Util
import kotlinx.android.synthetic.main.schedule_item.view.*

class ScheduleAdapter(val flight: List<Flight>, val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.schedule_item, p0, false)
        return ViewHolder(view, scheduleListener)
    }

    override fun getItemCount() = flight.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = flight[position]
        val timeArrival = Util.dateFormat().format(model.arrival.scheduledTimeLocal.dateTime)
        holder.arrival.text = String.format("%s at %s", model.arrival.airportCode, timeArrival)
        val timeDeparture = Util.dateFormat().format(model.departure.scheduledTimeLocal.dateTime)
        holder.departure.text = String.format("%s at %s ", model.departure.airportCode, timeDeparture)

    }

    class ViewHolder(val view: View, val scheduleListener: ScheduleListener) : RecyclerView.ViewHolder(view) {
        val arrival: TextView = view.detailsArrival
        val departure: TextView = view.detailsDeparture
        init {
            view.setOnClickListener {
                scheduleListener.onSelected()
            }
        }
    }

    companion object {
        interface ScheduleListener{
            fun onSelected()
        }
    }
}
