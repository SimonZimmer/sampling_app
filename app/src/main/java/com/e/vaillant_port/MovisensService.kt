package com.e.vaillant_port

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.e.vaillant_port.Main_App.Companion.CHANNEL_ID

class MovisensService : Service() {

    val machineState = com.e.vaillant_port.MachineState("machine")

    private var movisens_receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ("com.e.vaillant_port.movisens_receiver")) {
                Log.d("debug_message", "Broadcast received")
                val data = intent.data
                Log.d("debug_message", "data = $data")

                val stopIntent = Intent(context, MainActivity::class.java)
                stopService(stopIntent)
            }

            machineState.setCurrentState(42)
            val state = machineState.getCurrentState()
            Log.d("debug_message", "current state of machine = $state")
        }
    }


    override fun onCreate(){
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("debug_message", "service started")

        val input = intent.getStringExtra("inputExtra")

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Movisens_Vaillant Port Service currently running")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        val intentFilter = IntentFilter("com.e.vaillant_port.movisens_receiver")
        intentFilter.addDataScheme("content")

        registerReceiver(this.movisens_receiver, intentFilter)

        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("debug_message", "service stopped")
        unregisterReceiver(this.movisens_receiver)
        super.onDestroy()
    }

    override fun onBind(intent:Intent): IBinder? {
        return null
    }
}