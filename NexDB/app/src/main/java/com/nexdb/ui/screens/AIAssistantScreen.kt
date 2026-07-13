package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun AIAssistantScreen(navController:NavController,connectionId:Long){
var q by remember{mutableStateOf("")};var r by remember{mutableStateOf("")}
Scaffold(topBar={TopAppBar(title={Text("AI Assistant")})}){padding->
Column(modifier=Modifier.padding(padding).fillMaxSize().padding(16.dp)){
OutlinedTextField(q,{q=it},modifier=Modifier.fillMaxWidth(),label={Text("Ask in natural language...")},minLines=3)
Spacer(Modifier.height(8.dp));Button({},modifier=Modifier.fillMaxWidth()){Text("Ask AI")}
if(r.isNotEmpty()){Spacer(Modifier.height(16.dp));Card(modifier=Modifier.fillMaxWidth()){Text(r,modifier=Modifier.padding(16.dp))}}
}}
