package com.example.gotify_clone.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun SendSmsScreen() {
    val ctx = LocalContext.current
    var phone by remember { mutableStateOf("") }
    var appId by remember { mutableStateOf("alerts") }
    var priority by remember { mutableStateOf("4") }
    var title by remember { mutableStateOf("Test") }
    var body by remember { mutableStateOf("Hello from NtifySMS!") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(phone, { phone = it }, label = { Text("Telefon destinatar") })
        OutlinedTextField(appId, { appId = it }, label = { Text("APPID (canal)") })
        OutlinedTextField(priority, { priority = it }, label = { Text("PRIORITY (1-5)") })
        OutlinedTextField(title, { title = it }, label = { Text("TITLE") })
        OutlinedTextField(body, { body = it }, label = { Text("BODY") }, minLines = 3)

        Button(onClick = {
            val msg = "${appId.trim()}|${priority.trim()}|${title.trim()}|${body.trim()}"
            sendSms(ctx, phone.trim(), msg)
        }) { Text("Trimite SMS") }
    }
}

private fun sendSms(context: Context, phone: String, message: String) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(context, "Lipsește permisiunea SEND_SMS", Toast.LENGTH_SHORT).show()
        return
    }
    val sm = SmsManager.getDefault()
    val parts = sm.divideMessage(message)
    sm.sendMultipartTextMessage(phone, null, parts, null, null)
    Toast.makeText(context, "Trimis.", Toast.LENGTH_SHORT).show()
}
