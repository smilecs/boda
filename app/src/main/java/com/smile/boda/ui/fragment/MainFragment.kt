package com.smile.boda.ui.fragment


import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smile.boda.R
import com.smile.boda.model.AirportModel
import com.smile.boda.ui.MainViewModel
import com.smile.boda.ui.activity.AirportListActivity
import com.smile.boda.ui.activity.MainActivity
import com.smile.boda.ui.util.Util
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 *
 */
private const val ORIGIN_REQ_CODE = 110
private const val DEST_REQ_CODE = 120

class MainFragment : AuthFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        authUser(viewModel)
        observeAirportsData(viewModel)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context, AirportListActivity::class.java)
        val airportListFragment = AirportListFragment()
        selectDestination.setOnClickListener {

            fragmentManager?.beginTransaction()?.apply {
                addToBackStack(null)
                airportListFragment.reqCode = MainActivity.DEST_REQ_CODE
                replace(R.id.container, airportListFragment)
            }?.commit()
            //startActivityForResult(intent, DEST_REQ_CODE)
        }
        selectOrigin.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                addToBackStack(null)
                airportListFragment.reqCode = MainActivity.ORIGIN_REQ_CODE
                replace(R.id.container, airportListFragment)
            }?.commit()
            //startActivityForResult(intent, ORIGIN_REQ_CODE)
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
        })
        viewModel.originAirport.observe(this, Observer { airport ->
            airport?.let {
                val nameFromList = Util.getTextFromAny(it.names["Name"])
                val infoText = getString(R.string.airport_caption, nameFromList, it.airportCode)
                originText.text = infoText
                viewModel.originModel = airport
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode) {
            val airportModel: AirportModel.Airport? = data?.getParcelableExtra(AirportListFragment.AIRPORT_DATA)
            airportModel?.let {
                val nameFromList = Util.getTextFromAny(it.names["Name"])
                val infoText = getString(R.string.airport_caption, nameFromList, airportModel?.airportCode)
                if (requestCode == ORIGIN_REQ_CODE) {
                    originText.text = infoText
                } else {
                    destinationText.text = infoText
                }
            }
        }
    }
}
