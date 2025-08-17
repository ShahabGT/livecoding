package ir.shahabazimi.domain.di

import ir.shahabazimi.domain.usecase.LoginUserUseCase
import ir.shahabazimi.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUserUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
}