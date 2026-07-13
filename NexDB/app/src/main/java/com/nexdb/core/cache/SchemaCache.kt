package com.nexdb.core.cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SchemaCache{
data class CacheEntry(val data:Any,val timestamp:Long=System.currentTimeMillis())
private val cache=mutableMapOf<String,CacheEntry>()
private val mutex=Mutex()
private val ttlMs=300_000L

suspend fun get(key:String):Any?=mutex.withLock{
cache[key]?.takeIf{System.currentTimeMillis()-it.timestamp<ttlMs}?.data}
suspend fun put(key:String,value:Any)=mutex.withLock{cache[key]=CacheEntry(value)}
suspend fun invalidate(prefix:String="")=mutex.withLock{
if(prefix.isEmpty())cache.clear()else cache.keys.removeAll{it.startsWith(prefix)}}
}
