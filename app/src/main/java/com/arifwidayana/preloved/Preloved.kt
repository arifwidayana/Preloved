package com.arifwidayana.preloved

import android.app.Application
import com.arifwidayana.shared.di.SharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Preloved : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Preloved)
            modules(
                SharedModule.getModules()
            )
        }
    }
}