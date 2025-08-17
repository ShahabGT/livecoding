package ir.shahabazimi.domain.usecase

import ir.shahabazimi.domain.repository.AuthRepository

class LoginUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, passwordHash: String) =
        repository.login(username, passwordHash)
}