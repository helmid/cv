package group.helmi.cv.util

import java.util.*
import kotlin.experimental.xor

object StringObfuscator {
    fun generateKey(): ByteArray {
        return UUID.randomUUID().toString().toByteArray()
    }

    fun crypt(plaintext: String, key: ByteArray): String {
        var crypt = plaintext.toByteArray()
        plaintext.forEachIndexed { index, plaintextByte ->
            key.forEach { keyByte ->
                crypt[index] = crypt[index] xor keyByte
            }
        }
        return Base64.getEncoder().encodeToString(crypt)
    }
}