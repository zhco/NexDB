package com.nexdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nexdb.ui.theme.NexDBTheme
import com.nexdb.ui.navigation.NexDBNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexDBTheme {
                NexDBNavGraph()
            }
        }
    }
}
