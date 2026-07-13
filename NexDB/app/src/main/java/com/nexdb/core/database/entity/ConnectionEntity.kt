package com.nexdb.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "connections")
data class ConnectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "host")
    val host: String,

    @ColumnInfo(name = "port")
    val port: Int = 3306,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String = "",

    @ColumnInfo(name = "default_database")
    val defaultDatabase: String = "",

    @ColumnInfo(name = "use_ssl")
    val useSsl: Boolean = false,

    @ColumnInfo(name = "use_ssh")
    val useSsh: Boolean = false,

    @ColumnInfo(name = "ssh_host")
    val sshHost: String = "",

    @ColumnInfo(name = "ssh_port")
    val sshPort: Int = 22,

    @ColumnInfo(name = "ssh_username")
    val sshUsername: String = "",

    @ColumnInfo(name = "ssh_password")
    val sshPassword: String = "",

    @ColumnInfo(name = "ssh_key_path")
    val sshKeyPath: String = "",

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_used_at")
    val lastUsedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "color_index")
    val colorIndex: Int = 0,

    @ColumnInfo(name = "group_name")
    val groupName: String = "Default"
)
