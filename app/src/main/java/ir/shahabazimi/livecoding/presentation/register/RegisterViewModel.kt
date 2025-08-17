package ir.shahabazimi.livecoding.presentation.register


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.shahabazimi.domain.usecase.LoginUserUseCase
import ir.shahabazimi.domain.usecase.RegisterUserUseCase
import ir.shahabazimi.domain.util.AuthResult
import ir.shahabazimi.livecoding.presentation.login.LoginState
import ir.shahabazimi.livecoding.util.PasswordHasher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUserUseCase) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun register(username: String, password: String) {
        viewModelScope.launch {
            registerUseCase(username, password).onEach { result ->
                when (result) {
                    is AuthResult.Success -> {
                        _state.value = RegisterState(registerSuccess = true)
                    }

                    is AuthResult.Error -> {
                        _state.value = RegisterState(error = result.message)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onLoading() {
        _state.value = RegisterState(isLoading = true)
    }
}