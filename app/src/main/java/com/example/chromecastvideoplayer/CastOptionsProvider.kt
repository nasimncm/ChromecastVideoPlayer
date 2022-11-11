package com.example.chromecastvideoplayer

import android.content.Context
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider

class CastOptionsProvider: OptionsProvider {
    companion object{
        const val CUSTOM_NAMESPACE = "urn:x-cast:custom_namespace"
    }
    override fun getCastOptions(context: Context): CastOptions {
        val supportedNamespace: MutableList<String> = ArrayList()
        supportedNamespace.add(CUSTOM_NAMESPACE)
        return CastOptions.Builder()
            .setReceiverApplicationId(context.getString(R.string.app_id))
            .setSupportedNamespaces(supportedNamespace)
            .build()
    }

    override fun getAdditionalSessionProviders(context: Context): MutableList<SessionProvider>? {
        return null
    }

}