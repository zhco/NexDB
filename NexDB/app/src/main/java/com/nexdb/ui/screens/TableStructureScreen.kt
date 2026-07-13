package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun TableStructureScreen(navController:NavController,connectionId:Long,database:String,table:String){
Scaffold(topBar={TopAppBar(title={Text("Structure: $table")})}){padding->
LazyColumn(modifier=Modifier.padding(padding).fillMaxSize()){item{Text("Column structure appears here",modifier=Modifier.padding(16.dp))}}}
}
