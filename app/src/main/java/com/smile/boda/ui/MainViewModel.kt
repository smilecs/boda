package com.smile.boda.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.past3.ketro.model.Wrapper
import com.smile.boda.api.request.AuthRequest
import com.smile.boda.model.KredentialModel

class MainViewModel : ViewModel() {

    fun authUser(): LiveData<Wrapper<KredentialModel>> {
        return AuthRequest().doRequest()
    }

}