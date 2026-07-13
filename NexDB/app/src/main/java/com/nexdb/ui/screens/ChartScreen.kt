package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ChartScreen(navController:NavController,connectionId:Long,database:String,table:String){
Scaffold(topBar={TopAppBar(title={Text("Chart: $table")})}){padding->
Box(modifier=Modifier.padding(padding).fillMaxSize().padding(16.dp)){Text("Charts rendered here (MPAndroidChart)")}}
}
