package ir.shahabazimi.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val username: String,
    @SerializedName("password_hash")
    val passwordHash: String
)