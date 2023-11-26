package com.streafy.amphibians

import android.app.Application
import com.streafy.amphibians.data.AppContainer
import com.streafy.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}