package com.vaillant_port

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log

class MovisensService : Service() {

    val machineState = com.vaillant_port.MachineState("machine")

    private var movisens_receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ("com.vaillant_port.movisens_receiver")) {
                Log.d("debug", "Broadcast received")
                val data = intent.data
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

        //val intentName = "com.vaillant_port.movisens_receiver"

        val intentFilter = IntentFilter("com.vaillant_port.movisens_receiver")
        intentFilter.addDataScheme("content")

        //val theFilter = IntentFilter()
        //theFilter.addAction(intentName)
        registerReceiver(this.movisens_receiver, intentFilter)

        return Service.START_STICKY
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