package com.example.cabapp

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CabApplication: Application() {

    companion object {
        var appContext: Context? = null
        var appScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}