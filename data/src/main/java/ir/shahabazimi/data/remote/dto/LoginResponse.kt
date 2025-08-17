package ir.shahabazimi.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val status: String,
    val message: String,
    val data: TokenData?
)

data class TokenData(
    val token: String,
    @SerializedName("expires_at")
    val expiresAt: String
)