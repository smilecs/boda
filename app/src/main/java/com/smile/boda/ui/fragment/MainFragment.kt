package com.smile.boda.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.past3.ketro.api.Kobserver
import com.smile.boda.R
import com.smile.boda.api.exception.ApiException
import com.smile.boda.api.response.ScheduleResp
import com.smile.boda.ui.MainViewModel
import com.smile.boda.ui.activity.MainActivity
import com.smile.boda.ui.activity.MapsActivity
import com.smile.boda.ui.adapter.TopScheduleAdapter
import com.smile.boda.ui.util.Util
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 *
 */


class MainFragment : AuthFragment(), TopScheduleAdapter.Companion.ScheduleListener {
    private var viewModel: MainViewModel by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        authUser(viewModel)
        observeAirportsData(viewModel)
        observeScheduleList(viewModel)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val airportListFragment = AirportListFragment()
        container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        selectDestination.setOnClickListener {

            fragmentManager?.beginTransaction()?.apply {
                addToBackStack(null)
                airportListFragment.reqCode = MainActivity.DEST_REQ_CODE
                replace(R.id.container, airportListFragment)
            }?.commit()
        }
        selectOrigin.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                addToBackStack(null)
                airportListFragment.reqCode = MainActivity.ORIGIN_REQ_CODE
                replace(R.id.container, airportListFragment)
            }?.commit()
        }
        refresh.setOnClickListener {
            viewModel.getSchedule()
        }
    }

    private fun observeAirportsData(viewModel: MainViewModel) {
        viewModel.destAirtport.observe(this, Observer { airport ->
            airport?.let {
                val nameFromList = Util.getTextFromAny(it.names["Name"])
                val infoText = getString(R.string.airport_caption, nameFromList, it.airportCode)
                destinationText.text = infoText
                viewModel.destModel = airport
            }
            showSchedule(viewModel
            )
        })
        viewModel.originAirport.observe(this, Observer { airport ->
            airport?.let {
                val nameFromList = Util.getTextFromAny(it.names["Name"])
                val infoText = getString(R.string.airport_caption, nameFromList, it.airportCode)
                originText.text = infoText
                viewModel.originModel = airport
            }
            showSchedule(viewModel)
        })
    }

    private fun showSchedule(mainViewModel: MainViewModel) {
        if (mainViewModel.destModel != null && mainViewModel.originModel != null) {
            progressBar.visibility = View.VISIBLE
            mainViewModel.getSchedule()
        }
    }

    private fun observeScheduleList(mainViewModel: MainViewModel) {
        mainViewModel.scheduleLiveData.observe(this, object : Kobserver<ScheduleResp>() {
            override fun onException(exception: Exception) {
                toggleSchView(false)
                if (exception is ApiException.ErrorConfig.NotFoundException) {
                    refresh.visibility = View.GONE
                }
                errorText.text = exception.message
            }

            override fun onSuccess(data: ScheduleResp) {
                toggleSchView(true)
                container.adapter = TopScheduleAdapter(data.scheduleResource.schedule,
                        this@MainFragment)
            }
        })
    }

    private fun toggleSchView(show: Boolean) {
        if (show) {
            container.visibility = View.VISIBLE
            errorText.visibility = View.GONE
            refresh.visibility = View.GONE

        } else {
            container.visibility = View.GONE
            errorText.visibility = View.VISIBLE
            refresh.visibility = View.VISIBLE
        }
        progressBar.visibility = View.GONE
    }

    override fun onSelected() {
        val intent = Intent(context, MapsActivity::class.java)
        intent.putExtra("origin", viewModel.originModel?.position?.coordinate)
        intent.putExtra("dest", viewModel.destModel?.position?.coordinate)
        startActivity(intent)
    }
}
