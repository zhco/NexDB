package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ExportScreen(navController:NavController,connectionId:Long){
var fmt by remember{mutableStateOf("CSV")}
Scaffold(topBar={TopAppBar(title={Text("Export Data")})}){padding->
Column(modifier=Modifier.padding(padding).padding(16.dp)){
Text("Export Format",style=MaterialTheme.typography.titleMedium)
Spacer(Modifier.height(8.dp))
Row{FilterChip(fmt=="CSV",{fmt="CSV"},label={Text("CSV")});Spacer(Modifier.width(8.dp));FilterChip(fmt=="JSON",{fmt="JSON"},label={Text("JSON")})}
Spacer(Modifier.height(16.dp));Button({}){Text("Export")}
}}
