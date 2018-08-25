package com.smile.boda.ui.fragment


import android.app.ProgressDialog
import android.support.v4.app.Fragment
import android.widget.Toast
import com.past3.ketro.api.Kobserver
import com.smile.boda.PrefUtil
import com.smile.boda.R
import com.smile.boda.model.KredentialModel
import com.smile.boda.ui.MainViewModel
import java.util.*


abstract class AuthFragment : Fragment() {

    protected fun authUser(mainViewModel: MainViewModel) {
        if (PrefUtil.getToken() == null || PrefUtil.getExpiry() <= Date().time) {
            PrefUtil.clearTokens()
            val progressBar = ProgressDialog(context).apply {
                isIndeterminate = true
                setCancelable(false)
                setMessage(getString(R.string.loading_msg))
                setCanceledOnTouchOutside(false)
            }
            progressBar.show()
            mainViewModel.authUser().observe(this, object : Kobserver<KredentialModel>() {
                override fun onException(exception: Exception) {
                    progressBar.dismiss()
                    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(data: KredentialModel) {
                    PrefUtil.setToken(data.token)
                    PrefUtil.setExpiry(data.expiry + Date().time)
                    progressBar.dismiss()
                    onAuth()
                }
            })
        }
    }

    open fun onAuth() {}
}
