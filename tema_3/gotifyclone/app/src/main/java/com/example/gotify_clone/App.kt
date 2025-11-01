package com.example.gotify_clone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.gotify_clone.ui.InboxScreen
import com.example.gotify_clone.ui.SendSmsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var tab by remember { mutableStateOf(0) }

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("NtifySMS") }) },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = tab == 0,
                        onClick = { tab = 0 },
                        icon = { Icon(Icons.Filled.List, null) },
                        label = { Text("Mesaje") }
                    )
                    NavigationBarItem(
                        selected = tab == 1,
                        onClick = { tab = 1 },
                        icon = { Icon(Icons.Filled.Send, null) },
                        label = { Text("Trimite") }
                    )
                }
            }
        ) { padd ->
            Box(Modifier.padding(padd)) { if (tab == 0) InboxScreen() else SendSmsScreen() }
        }
    }
}
