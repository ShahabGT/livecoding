package ir.shahabazimi.livecoding.di

import ir.shahabazimi.livecoding.presentation.login.LoginViewModel
import ir.shahabazimi.livecoding.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}