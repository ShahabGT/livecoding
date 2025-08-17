package ir.shahabazimi.livecoding.util

import java.security.MessageDigest

object PasswordHasher {
    fun sha256(input: String): String {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }
}