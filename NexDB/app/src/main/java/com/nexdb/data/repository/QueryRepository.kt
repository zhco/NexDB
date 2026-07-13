package com.nexdb.data.repository
import com.nexdb.core.mysql.*

class QueryRepository{
private var session:MySQLSession?=null
suspend fun connect(config:ConnectionConfig){session?.disconnect();session=MySQLSession(config).also{it.connect()}}
suspend fun execute(sql:String)=session?.execute(sql)?:throw IllegalStateException("Not connected")
suspend fun getDatabases()=session?.getDatabases()?:emptyList()
suspend fun getTables(db:String)=session?.getTables(db)?:emptyList()
suspend fun getTableSchema(db:String,tbl:String)=session?.getTableSchema(db,tbl)?:emptyList()
suspend fun disconnect(){session?.disconnect();session=null}
fun isConnected()=session?.isConnected==true
}
