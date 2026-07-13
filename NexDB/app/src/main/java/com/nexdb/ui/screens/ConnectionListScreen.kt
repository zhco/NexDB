package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nexdb.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ConnectionListScreen(navController:NavController){
Scaffold(topBar={TopAppBar(title={Text("NexDB")})},
floatingActionButton={FloatingActionButton(onClick={navController.navigate(Screen.ConnectionEdit.createRoute())}){Icon(Icons.Default.Add,contentDescription="Add")}}
){padding->LazyColumn(modifier=Modifier.padding(padding).fillMaxSize()){item{Text("No connections yet. Tap + to add.",modifier=Modifier.padding(16.dp))}}}
}
