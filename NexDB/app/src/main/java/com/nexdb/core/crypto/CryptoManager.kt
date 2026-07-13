package com.nexdb.core.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.nexdb.NexDBApplication
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.KeyStore

object CryptoManager {

    private const val KEY_ALIAS = "nexdb_master_key"
    private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"

    private val masterKey by lazy {
        MasterKey.Builder(NexDBApplication.instance)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val encryptedPrefs by lazy {
        EncryptedSharedPreferences.create(
            NexDBApplication.instance,
            "nexdb_secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveSecure(key: String, value: String) {
        encryptedPrefs.edit().putString(key, value).apply()
    }

    fun getSecure(key: String): String? {
        return encryptedPrefs.getString(key, null)
    }

    fun removeSecure(key: String) {
        encryptedPrefs.edit().remove(key).apply()
    }

    fun encrypt(plainText: String, keyAlias: String = KEY_ALIAS): ByteArray {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)
        val secretKey = keyStore.getKey(keyAlias, null)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
        return iv + encrypted
    }

    fun decrypt(encryptedData: ByteArray, keyAlias: String = KEY_ALIAS): String {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)
        val secretKey = keyStore.getKey(keyAlias, null)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = encryptedData.copyOfRange(0, 12)
        val data = encryptedData.copyOfRange(12, encryptedData.size)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        return String(cipher.doFinal(data), Charsets.UTF_8)
    }
}
