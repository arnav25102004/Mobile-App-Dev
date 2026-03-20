package com.example.arnav_splash_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val TAG = "LifecycleDemo"
    private lateinit var logTextView: TextView
    private var logMessages = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logTextView = findViewById(R.id.logTextView)
        logTextView.text = ""
        addLog("onCreate() called")
    }
    override fun onStart() {
        super.onStart()
        addLog("onStart() called")
    }
    override fun onResume() {
        super.onResume()
        addLog("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        addLog("onPause() called")
    }
    override fun onStop() {
        super.onStop()
        addLog("onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        addLog("onDestroy() called")
    }
    private fun addLog(message: String) {
        Log.d(TAG, message)
        logMessages += "$message\n"
        logTextView.text = logMessages
    }
}