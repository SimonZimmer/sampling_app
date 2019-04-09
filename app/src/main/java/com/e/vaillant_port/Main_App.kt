package com.e.vaillant_port

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class Main_App : Application(){
    companion object {
        val CHANNEL_ID = "MovisensServiceChannel"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    // TODO: make this backwards compatible for API Level < 26
    fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "MovisensServiceChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}