package com.nexdb

import android.app.Application

class NexDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NexDBApplication
            private set
    }
}
