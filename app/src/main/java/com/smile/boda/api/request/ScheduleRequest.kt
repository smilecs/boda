package com.smile.boda.api.request

import com.past3.ketro.api.GenericRequestHandler
import com.smile.boda.api.BodaService
import com.smile.boda.api.exception.ApiException
import com.smile.boda.api.net.NetModule
import com.smile.boda.api.response.ScheduleResp
import com.smile.boda.ui.util.Util
import retrofit2.Call
import java.util.*

class ScheduleRequest(val origin: String, val dest: String) : GenericRequestHandler<ScheduleResp>() {
    override fun makeRequest(): Call<ScheduleResp> {
        return NetModule.provideRetrofit().create(BodaService::class.java)
                .getSchedule(origin, dest, Util.dateForServer().format(Date()))
    }

    override val errorHandler = ApiException()
}