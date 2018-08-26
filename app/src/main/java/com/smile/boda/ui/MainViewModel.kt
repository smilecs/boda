package com.smile.boda.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.past3.ketro.model.Wrapper
import com.smile.boda.api.request.AirportRequest
import com.smile.boda.api.request.AuthRequest
import com.smile.boda.api.request.ScheduleRequest
import com.smile.boda.api.response.AirportResp
import com.smile.boda.api.response.ScheduleResp
import com.smile.boda.model.AirportModel
import com.smile.boda.model.KredentialModel

class MainViewModel : ViewModel() {
    private val airportLiveData = MutableLiveData<Wrapper<AirportResp>>()
    val airportList = mutableListOf<AirportModel.Airport>()
    val originAirport = MutableLiveData<AirportModel.Airport>()
    val destAirtport = MutableLiveData<AirportModel.Airport>()
    val scheduleLiveData = MutableLiveData<Wrapper<ScheduleResp>>()
    var originModel: AirportModel.Airport? = null
    var destModel: AirportModel.Airport? = null

    fun authUser(): LiveData<Wrapper<KredentialModel>> {
        return AuthRequest().doRequest()
    }

    fun getAirports(offset: Int = 0) = AirportRequest(offset).executeRequest(airportLiveData)

    fun getSchedule() {
        if (originModel != null && destModel != null) {
            ScheduleRequest(originModel!!.airportCode, destModel!!.airportCode)
                    .executeRequest(scheduleLiveData)
        }
    }

    fun airportData(): LiveData<Wrapper<AirportResp>> = airportLiveData
}