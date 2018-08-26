package com.smile.boda.ui.adapter

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smile.boda.R
import com.smile.boda.model.Schedule
import kotlinx.android.synthetic.main.top_schedule_item.view.*


class TopScheduleAdapter(val flight: List<Schedule>, val scheduleListener: ScheduleListener) : RecyclerView.Adapter<TopScheduleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.top_schedule_item, p0, false)
        return ViewHolder(view, scheduleListener)
    }

    override fun getItemCount() = flight.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = flight[position]
        holder.flights.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(holder.view.context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = ScheduleAdapter(model.flight)
        }
        holder.journey.text = model.totalJourney.duration.replace("P", "")

    }

    class ViewHolder(val view: View, val scheduleListener: ScheduleListener) : RecyclerView.ViewHolder(view) {
        val flights: RecyclerView = view.recyclerView
        val journey: TextView = view.totalJourney

        init {
            view.setOnClickListener {
                scheduleListener.onSelected()
            }
        }
    }

    companion object {
        interface ScheduleListener {
            fun onSelected()
        }
    }
}
