package com.vaillant_port

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.app.Notification


class MovisensService : Service() {

    val machineState = com.vaillant_port.MachineState("machine")

    private var movisens_receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ("com.vaillant_port.movisens_receiver")) {
                Log.d("debug", "Broadcast received")
                val data = intent.dataString
                Log.d("debug", "data = $data")

                val stopIntent = Intent(context, MainActivity::class.java)
                stopService(stopIntent)
            }

            machineState.setCurrentState(42)
            val state = machineState.getCurrentState()
            Log.d("debug", "current state of machine = $state")
        }
    }

    //TODO implement stop service
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("debug","service started")

        val intentName = "com.vaillant_port.movisens_receiver"
        val theFilter = IntentFilter()
        theFilter.addAction(intentName)
        registerReceiver(this.movisens_receiver, theFilter)

        return Service.START_STICKY
    }

    private fun showLocationNotification() {
        val notification = Notification.Builder(this)
            .setContentTitle("Service")
            .setContentText("Service Started Successfully")
            .setSmallIcon(R.mipmap.sym_def_app_icon)
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent): IBinder? {
        //TODO for communication return IBinder implementation
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(this.movisens_receiver)
        Log.d("debug", "service stopped")
    }
}