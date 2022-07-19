package group.helmi.cv.util

import java.util.*
import kotlin.experimental.xor

object StringObfuscator {
    fun generateKey(): ByteArray {
        return UUID.randomUUID().toString().toByteArray()
    }

    fun crypt(plaintext: ByteArray, key: ByteArray): String {
        val crypt = plaintext
        plaintext.forEachIndexed { index, _ ->
            key.forEach { keyByte ->
                crypt[index] = crypt[index] xor keyByte
            }
        }
        return Base64.getEncoder().encodeToString(crypt)
    }
}