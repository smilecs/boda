package com.smile.boda.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.past3.ketro.api.Kobserver
import com.smile.boda.R
import com.smile.boda.api.response.AirportResp
import com.smile.boda.model.AirportModel
import com.smile.boda.ui.MainViewModel
import com.smile.boda.ui.adapter.AirportAdapter
import com.smile.boda.ui.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.airport_list.*
import kotlin.properties.Delegates

class AirportListFragment : Fragment(), AirportAdapter.Companion.AirportSelector {
    var airportAdapter: AirportAdapter by Delegates.notNull()

    var count: Int? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.airport_list, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initUi(mainViewModel)
        observeListData(mainViewModel)
    }

    override fun onAirportSelected(airport: AirportModel.Airport) {

    }

    private fun initUi(mainViewModel: MainViewModel) {
        val linearLayoutManager = LinearLayoutManager(context)
        airportAdapter = AirportAdapter(mainViewModel.airportList, this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = airportAdapter
        }.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                count?.let {
                    if (it > totalItemsCount) {
                        mainViewModel.getAirports(totalItemsCount)
                    }
                }
            }
        })
    }

    private fun observeListData(mainViewModel: MainViewModel) =
            mainViewModel.airportData().observe(this, object : Kobserver<AirportResp>() {
                override fun onException(exception: Exception) {
                }

                override fun onSuccess(data: AirportResp) {
                    mainViewModel.airportList.addAll(data.airportResource.airports.airports)
                    airportAdapter.notifyDataSetChanged()
                }
            })
}
