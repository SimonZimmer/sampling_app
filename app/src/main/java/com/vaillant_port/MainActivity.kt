package com.vaillant_port

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug","app started")
        val launchIntent = Intent(this, MovisenseService::class.java)
        startService(launchIntent)
        finish()
    }

}
