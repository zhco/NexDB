package com.nexdb.core.mysql
import kotlinx.coroutines.*
import java.sql.DriverManager
import java.util.Properties

data class QueryResult(val columns:List<String>,val rows:List<List<Any?>>,val rowCount:Int,val executionTimeMs:Long)
data class ConnectionConfig(val host:String,val port:Int=3306,val username:String,val password:String,val database:String="",val useSSL:Boolean=true)
data class SSHTunnelConfig(val sshHost:String,val sshPort:Int=22,val sshUser:String,val sshPassword:String?=null,val sshKeyPath:String?=null)

class MySQLSession(private val config:ConnectionConfig){
private var conn:java.sql.Connection?=null
suspend fun connect()=withContext(Dispatchers.IO){
val p=Properties().apply{setProperty("user",config.username);setProperty("password",config.password);setProperty("useSSL",config.useSSL.toString());setProperty("allowPublicKeyRetrieval","true");setProperty("connectTimeout","10000");setProperty("socketTimeout","30000")}
val db=if(config.database.isNotEmpty())config.database else ""
conn=DriverManager.getConnection("jdbc:mysql://${config.host}:${config.port}/$db?serverTimezone=UTC&characterEncoding=utf8mb4&useUnicode=true",p)
}

suspend fun execute(sql:String):QueryResult=withContext(Dispatchers.IO){
val c=conn?:throw IllegalStateException("Not connected")
val t0=System.currentTimeMillis()
c.createStatement().use{s->
if(s.execute(sql)){val rs=s.resultSet;val m=rs.metaData
val cols=(1..m.columnCount).map{m.getColumnName(it)}
val rows=mutableListOf<List<Any?>>();var n=0
while(rs.next()&&n<10000){rows.add((1..m.columnCount).map{rs.getObject(it)});n++}
QueryResult(cols,rows,n,System.currentTimeMillis()-t0)}
else QueryResult(emptyList(),emptyList(),s.updateCount,System.currentTimeMillis()-t0)
}}

suspend fun getDatabases()=withContext(Dispatchers.IO){
conn!!.createStatement().use{
val rs=it.executeQuery("SHOW DATABASES")
generateSequence{if(rs.next())rs.getString(1)else null}.toList()
}}
suspend fun getTables(db:String)=withContext(Dispatchers.IO){
conn!!.createStatement().use{
val rs=it.executeQuery("SHOW TABLES FROM `$db`")
generateSequence{if(rs.next())rs.getString(1)else null}.toList()
}}
suspend fun getTableSchema(db:String,tbl:String)=withContext(Dispatchers.IO){
conn!!.createStatement().use{
val rs=it.executeQuery("DESCRIBE `$db`.`$tbl`")
val c=mutableListOf<Map<String,Any?>>()
while(rs.next())c.add(mapOf("field" to rs.getString("Field"),"type" to rs.getString("Type"),"null" to rs.getString("Null"),"key" to rs.getString("Key"),"default" to rs.getString("Default"),"extra" to rs.getString("Extra")))
c}}
suspend fun disconnect()=withContext(Dispatchers.IO){conn?.close();conn=null}
val isConnected get()=conn?.isClosed?.not()==true
}
