package com.smile.boda.ui.fragment

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.past3.ketro.api.Kobserver
import com.smile.boda.PrefUtil
import com.smile.boda.R
import com.smile.boda.api.response.AirportResp
import com.smile.boda.model.AirportModel
import com.smile.boda.ui.MainViewModel
import com.smile.boda.ui.adapter.AirportAdapter
import com.smile.boda.ui.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.airport_list.*
import kotlin.properties.Delegates

class AirportListFragment : AuthFragment(), AirportAdapter.Companion.AirportSelector {
    var airportAdapter: AirportAdapter by Delegates.notNull()

    companion object {
        const val AIRPORT_DATA = "airport_data"
    }

    var mainViewModel: MainViewModel by Delegates.notNull()

    var count: Int? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.airport_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initUi(mainViewModel)
        observeListData(mainViewModel)
        getData(mainViewModel)
    }

    override fun onAirportSelected(airport: AirportModel.Airport) {
        activity?.setResult(Activity.RESULT_OK, Intent().putExtra(AIRPORT_DATA, airport))
        activity?.finish()
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

    private fun getData(mainViewModel: MainViewModel) {
        if (PrefUtil.getToken() == null) {
            authUser(mainViewModel)
        } else {
            mainViewModel.getAirports()
        }
    }

    override fun onAuth() {
        super.onAuth()
        mainViewModel.getAirports()
    }
}
