package com.mindyhsu.chip

import android.app.Application
import timber.log.Timber
import kotlin.properties.Delegates

class AppApplication : Application() {

    companion object {
        var instance: AppApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.TIMBER_VISIABLE) {
            Timber.plant(Timber.DebugTree())
        }
    }
}