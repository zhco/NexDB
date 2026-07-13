package com.nexdb.core.ai
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

data class AIResponse(val sql:String,val explanation:String)
data class AIConfig(val apiUrl:String,val apiKey:String,val model:String="gpt-3.5-turbo")

class AIAssistant(private val config:AIConfig){
suspend fun generateSQL(nl:String,schemaCtx:String=""):AIResponse=withContext(Dispatchers.IO){
val prompt="You are a MySQL expert. Schema: $schemaCtx\nRequest: $nl\nRespond ONLY with JSON: {\"sql\":\"...\",\"explanation\":\"...\"}"

val body=JSONObject().apply{
put("model",config.model)
put("messages",org.json.JSONArray().apply{put(JSONObject().apply{put("role","user");put("content",prompt)})})
put("temperature",0.1);put("max_tokens",1000)}

val conn=URL(config.apiUrl).openConnection() as HttpURLConnection
conn.requestMethod="POST"
conn.setRequestProperty("Content-Type","application/json")
conn.setRequestProperty("Authorization","Bearer ${config.apiKey}")
conn.doOutput=true
conn.outputStream.write(body.toString().toByteArray())

val resp=conn.inputStream.bufferedReader().readText()
val json=JSONObject(resp)
val msg=json.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")
val t=msg.trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
val r=JSONObject(t)
AIResponse(r.getString("sql"),r.getString("explanation"))}
}
