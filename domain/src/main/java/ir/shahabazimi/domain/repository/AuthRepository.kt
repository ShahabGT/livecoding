package ir.shahabazimi.domain.repository

import ir.shahabazimi.domain.util.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(username: String, password: String): Flow<AuthResult<Unit>>
    suspend fun login(username: String, passwordHash: String): Flow<AuthResult<String>>
}