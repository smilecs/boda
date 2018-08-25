package com.smile.boda.api

import com.smile.boda.api.net.Urls
import com.smile.boda.model.KredentialModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BodaService {

    @FormUrlEncoded
    @POST(Urls.OAUTH)
    fun authenticate(@Field("client_id") clientId: String,
                     @Field("client_secret") secret: String,
                     @Field("grant_type") type: String):Call<KredentialModel>

}