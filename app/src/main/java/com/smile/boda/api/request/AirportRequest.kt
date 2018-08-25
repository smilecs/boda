package com.smile.boda.api.request

import com.past3.ketro.api.ApiErrorHandler
import com.past3.ketro.api.GenericRequestHandler
import com.smile.boda.api.BodaService
import com.smile.boda.api.exception.ApiException
import com.smile.boda.api.net.NetModule
import com.smile.boda.api.response.AirportResp
import retrofit2.Call

class AirportRequest(val offset: Int) : GenericRequestHandler<AirportResp>() {
    private val limit = 2

    override fun makeRequest(): Call<AirportResp> {
        return NetModule.provideRetrofit().create(BodaService::class.java).getAirports(limit, 77)
    }

    override val errorHandler: ApiErrorHandler = ApiException()
}
