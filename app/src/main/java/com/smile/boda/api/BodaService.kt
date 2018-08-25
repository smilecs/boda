package com.smile.boda.api

import com.smile.boda.api.net.Urls
import com.smile.boda.api.response.AirportResp
import com.smile.boda.model.KredentialModel
import retrofit2.Call
import retrofit2.http.*

interface BodaService {

    @FormUrlEncoded
    @POST(Urls.OAUTH)
    fun authenticate(@Field("client_id") clientId: String,
                     @Field("client_secret") secret: String,
                     @Field("grant_type") type: String): Call<KredentialModel>

    @GET(Urls.AIRPORT_ROUTE)
    fun getAirports(@Query("limit") limit: Int, @Query("offset") offset: Int):Call<AirportResp>

}