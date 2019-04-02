package com.e.vaillant_port

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService()
        finish()
    }

    fun startService() {
        val input = "vaillant cloud management service currently running"
        val serviceIntent = Intent(this, MovisensService::class.java)
        serviceIntent.putExtra("inputExtra", input)

        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService() {
        val serviceIntent = Intent(this, MovisensService::class.java)
        stopService(serviceIntent)
    }
}
