package com.nexdb.core.ssh
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import kotlinx.coroutines.*
import com.nexdb.core.mysql.SSHTunnelConfig

class SSHTunnelManager(private val config:SSHTunnelConfig){
private var session:Session?=null
var localPort:Int=0;private set

suspend fun connect():Int=withContext(Dispatchers.IO){
val jsch=JSch()
if(config.sshKeyPath!=null)jsch.addIdentity(config.sshKeyPath)
session=jsch.getSession(config.sshUser,config.sshHost,config.sshPort)
if(config.sshPassword!=null)session!!.setPassword(config.sshPassword)
session!!.setConfig("StrictHostKeyChecking","no")
session!!.setConfig("PreferredAuthentications","publickey,password,keyboard-interactive")
session!!.connect(10000)
localPort=session!!.setPortForwardingL(0,"127.0.0.1",3306)
localPort}
suspend fun disconnect()=withContext(Dispatchers.IO){session?.disconnect();session=null}
}
