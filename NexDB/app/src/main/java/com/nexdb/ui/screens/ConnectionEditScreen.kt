package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ConnectionEditScreen(navController:NavController,connectionId:Long){
var host by remember{mutableStateOf("")};var port by remember{mutableStateOf("3306")}
var user by remember{mutableStateOf("")};var pass by remember{mutableStateOf("")};var db by remember{mutableStateOf("")}
Scaffold(topBar={TopAppBar(title={Text(if(connectionId==-1L)"New Connection" else "Edit Connection")})}){padding->
Column(modifier=Modifier.padding(padding).padding(16.dp).verticalScroll(rememberScrollState())){
OutlinedTextField(host,{host=it},label={Text("Host")},modifier=Modifier.fillMaxWidth())
Spacer(Modifier.height(8.dp));OutlinedTextField(port,{port=it},label={Text("Port")},modifier=Modifier.fillMaxWidth())
Spacer(Modifier.height(8.dp));OutlinedTextField(user,{user=it},label={Text("Username")},modifier=Modifier.fillMaxWidth())
Spacer(Modifier.height(8.dp));OutlinedTextField(pass,{pass=it},label={Text("Password")},modifier=Modifier.fillMaxWidth(),visualTransformation=PasswordVisualTransformation())
Spacer(Modifier.height(8.dp));OutlinedTextField(db,{db=it},label={Text("Database (optional)")},modifier=Modifier.fillMaxWidth())
Spacer(Modifier.height(24.dp));Button({navController.popBackStack()},modifier=Modifier.fillMaxWidth()){Text("Save")}
}}
}
