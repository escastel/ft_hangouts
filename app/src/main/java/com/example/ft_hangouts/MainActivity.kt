package com.example.ft_hangouts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.ft_hangouts.ui.MainApp
import com.example.ft_hangouts.utils.DateUtils

class MainActivity : ComponentActivity() {
    private var pauseTime: Long = 0

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MainApp(windowSizeClass)
        }
    }

    override fun onPause() {
        super.onPause()
        pauseTime = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        if (pauseTime != 0L) {
            val dateString = DateUtils.formatDateTime(pauseTime)
            val msg = getString(R.string.background_msg, dateString)
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }
}