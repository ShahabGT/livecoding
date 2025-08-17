package ir.shahabazimi.domain.usecase

import ir.shahabazimi.domain.repository.AuthRepository

class RegisterUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) =
        repository.register(username, password)
}