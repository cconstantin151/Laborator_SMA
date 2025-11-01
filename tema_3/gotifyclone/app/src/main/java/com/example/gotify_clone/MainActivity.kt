package com.example.gotify_clone

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gotify_clone.di.Di

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Di.init(applicationContext)

        requestPermissionsIfNeeded()

        setContent { App() }
    }

    private fun requestPermissionsIfNeeded() {
        val need = mutableListOf<String>()
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
            need += Manifest.permission.RECEIVE_SMS
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
            need += Manifest.permission.SEND_SMS
        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
            need += Manifest.permission.POST_NOTIFICATIONS

        if (need.isNotEmpty()) requestPermissions(need.toTypedArray(), 1001)
    }
}
