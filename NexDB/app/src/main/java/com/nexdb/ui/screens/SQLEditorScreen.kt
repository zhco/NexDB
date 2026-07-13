package com.nexdb.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun SQLEditorScreen(navController:NavController,connectionId:Long){
var sql by remember{mutableStateOf("")};var result by remember{mutableStateOf("")}
Scaffold(topBar={TopAppBar(title={Text("SQL Editor")})}){padding->
Column(modifier=Modifier.padding(padding).fillMaxSize()){
OutlinedTextField(sql,{sql=it},modifier=Modifier.fillMaxWidth().height(150.dp).padding(8.dp),textStyle=LocalTextStyle.current.copy(fontFamily=FontFamily.Monospace,fontSize=14.sp),label={Text("SQL Query")})
Row(modifier=Modifier.padding(horizontal=8.dp)){Button({}){Text("Run")};Spacer(Modifier.width(8.dp));OutlinedButton({sql=""}){Text("Clear")}}
LazyColumn(modifier=Modifier.padding(8.dp).weight(1f)){if(result.isNotEmpty())item{Text(result,fontFamily=FontFamily.Monospace)}}
}}
