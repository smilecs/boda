package com.smile.boda.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.past3.ketro.model.Wrapper
import com.smile.boda.api.request.AirportRequest
import com.smile.boda.api.request.AuthRequest
import com.smile.boda.api.response.AirportResp
import com.smile.boda.model.AirportModel
import com.smile.boda.model.KredentialModel

class MainViewModel : ViewModel() {
    private val airportLiveData = MutableLiveData<Wrapper<AirportResp>>()
    val airportList = mutableListOf<AirportModel.Airport>()

    fun authUser(): LiveData<Wrapper<KredentialModel>> {
        return AuthRequest().doRequest()
    }

    fun getAirports(offset: Int = 0) {
        AirportRequest(offset).executeRequest(airportLiveData)
    }

    fun airportData(): LiveData<Wrapper<AirportResp>> = airportLiveData
}