package com.nexdb.data.repository
import kotlinx.coroutines.*
import java.io.*
import com.opencsv.CSVWriter
import com.nexdb.core.mysql.MySQLSession

class ExportRepository(private val cacheDir:File){
suspend fun exportCSV(session:MySQLSession,sql:String,out:File):Int=withContext(Dispatchers.IO){
val r=session.execute(sql)
CSVWriter(FileWriter(out)).use{w->w.writeNext(r.columns.toTypedArray());r.rows.forEach{w.writeNext(it.map{v->v?.toString()?:"NULL"}.toTypedArray())}}
r.rows.size}
suspend fun exportJSON(session:MySQLSession,sql:String,out:File):Int=withContext(Dispatchers.IO){
val r=session.execute(sql)
val sb=StringBuilder("[\n")
r.rows.forEachIndexed{i,row->sb.append("  {");r.columns.forEachIndexed{j,col->sb.append("\"$col\":\"${(row[j]?.toString()?:"null").replace("\"","\\\"")}\"");if(j<r.columns.size-1)sb.append(", ")};sb.append("}");if(i<r.rows.size-1)sb.append(",");sb.append("\n")}
sb.append("]\n");out.writeText(sb.toString());r.rows.size}
}
