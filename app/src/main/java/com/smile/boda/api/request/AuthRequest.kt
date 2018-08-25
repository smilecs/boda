package com.smile.boda.api.request

import com.past3.ketro.api.GenericRequestHandler
import com.smile.boda.App
import com.smile.boda.R
import com.smile.boda.api.BodaService
import com.smile.boda.api.net.NetModule
import com.smile.boda.model.KredentialModel
import retrofit2.Call

class AuthRequest : GenericRequestHandler<KredentialModel>() {

    override fun makeRequest(): Call<KredentialModel> {
        return with(NetModule.provideRetrofit()) {
            val res = App.getsInstance().resources
            create(BodaService::class.java).authenticate(res.getString(R.string.client_id),
                    res.getString(R.string.client_secret),
                    res.getString(R.string.bearer))
        }
    }
}