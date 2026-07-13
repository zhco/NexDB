package com.nexdb.data.repository
import com.nexdb.core.database.dao.ConnectionDao
import com.nexdb.core.database.entity.ConnectionEntity
import com.nexdb.core.crypto.CryptoManager
import kotlinx.coroutines.flow.Flow

class ConnectionRepository(private val dao:ConnectionDao,private val crypto:CryptoManager){
val allConnections:Flow<List<ConnectionEntity>>=dao.getAllConnections()
suspend fun insert(conn:ConnectionEntity){dao.insert(conn.copy(password=crypto.encrypt(conn.password),sshPassword=conn.sshPassword?.let{crypto.encrypt(it)}))}
suspend fun update(conn:ConnectionEntity){dao.update(conn.copy(password=crypto.encrypt(conn.password),sshPassword=conn.sshPassword?.let{crypto.encrypt(it)}))}
suspend fun delete(conn:ConnectionEntity)=dao.delete(conn)
suspend fun getById(id:Long):ConnectionEntity?=dao.getById(id)?.copy(password=crypto.decrypt(it.password),sshPassword=it.sshPassword?.let{p->crypto.decrypt(p)})
}
