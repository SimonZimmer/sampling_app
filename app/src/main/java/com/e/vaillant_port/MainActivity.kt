package com.e.vaillant_port

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val serviceIntent = Intent(this, MovisensService::class.java)
        startService(serviceIntent)
        finish()
    }

    fun startService(v: View) {
        val input = "Marcellus Wallace"
        val serviceIntent = Intent(this, MovisensService::class.java)
        serviceIntent.putExtra("inputExtra", input)

        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(v: View) {
        val serviceIntent = Intent(this, MovisensService::class.java)
        stopService(serviceIntent)
    }
}
