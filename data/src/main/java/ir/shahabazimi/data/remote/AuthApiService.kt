package ir.shahabazimi.data.remote

import ir.shahabazimi.data.remote.dto.LoginRequest
import ir.shahabazimi.data.remote.dto.LoginResponse
import ir.shahabazimi.data.remote.dto.RegisterRequest
import ir.shahabazimi.data.remote.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}