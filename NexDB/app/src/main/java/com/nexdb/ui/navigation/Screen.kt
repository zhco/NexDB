package com.nexdb.ui.navigation
sealed class Screen(val route:String){
object ConnectionList:Screen("connections")
object ConnectionEdit:Screen("connection_edit/{connectionId}"){fun createRoute(id:Long=-1)="connection_edit/$id"}
object SQLEditor:Screen("sql_editor/{connectionId}"){fun createRoute(id:Long)="sql_editor/$id"}
object DataBrowser:Screen("data_browser/{connectionId}/{database}/{table}"){fun createRoute(c:Long,db:String,t:String)="data_browser/$c/$db/$t"}
object TableStructure:Screen("table_structure/{connectionId}/{database}/{table}"){fun createRoute(c:Long,db:String,t:String)="table_structure/$c/$db/$t"}
object Export:Screen("export/{connectionId}"){fun createRoute(id:Long)="export/$id"}
object Chart:Screen("chart/{connectionId}/{database}/{table}"){fun createRoute(c:Long,db:String,t:String)="chart/$c/$db/$t"}
object AIAssistant:Screen("ai_assistant/{connectionId}"){fun createRoute(id:Long)="ai_assistant/$id"}
}
