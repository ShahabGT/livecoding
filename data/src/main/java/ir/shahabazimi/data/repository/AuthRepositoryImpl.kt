package ir.shahabazimi.data.repository

import ir.shahabazimi.data.remote.AuthApiService
import ir.shahabazimi.data.remote.dto.LoginRequest
import ir.shahabazimi.data.remote.dto.RegisterRequest
import ir.shahabazimi.domain.repository.AuthRepository
import ir.shahabazimi.domain.util.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {

    override suspend fun register(username: String, password: String): Flow<AuthResult<Unit>> =
        flow {
            try {
                val response = apiService.register(RegisterRequest(username, password))
                if (response.isSuccessful) {
                    emit(AuthResult.Success(Unit))
                } else {
                    emit(AuthResult.Error(response.body()?.message ?: "Registration failed"))
                }
            } catch (e: HttpException) {
                emit(AuthResult.Error(e.localizedMessage ?: "An unexpected HTTP error occurred"))
            } catch (e: IOException) {
                emit(AuthResult.Error("Couldn't reach server. Check your internet connection."))
            }
        }

    override suspend fun login(username: String, passwordHash: String): Flow<AuthResult<String>> =
        flow {
            try {
                val response = apiService.login(LoginRequest(username, passwordHash))
                if (response.isSuccessful && response.body()?.data?.token != null) {
                    emit(AuthResult.Success(response.body()!!.data!!.token))
                } else {
                    emit(AuthResult.Error(response.body()?.message ?: "Invalid credentials"))
                }
            } catch (e: HttpException) {
                emit(AuthResult.Error(e.localizedMessage ?: "An unexpected HTTP error occurred"))
            } catch (e: IOException) {
                emit(AuthResult.Error("Couldn't reach server. Check your internet connection."))
            }
        }
}