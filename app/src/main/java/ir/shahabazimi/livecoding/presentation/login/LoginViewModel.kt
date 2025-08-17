package ir.shahabazimi.livecoding.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.shahabazimi.domain.usecase.LoginUserUseCase
import ir.shahabazimi.domain.util.AuthResult
import ir.shahabazimi.livecoding.util.PasswordHasher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val passwordHash = PasswordHasher.sha256(password)
            loginUserUseCase(username, passwordHash).onEach { result ->
                when (result) {
                    is AuthResult.Success -> {
                        _state.value = LoginState(loginSuccess = true)
                    }

                    is AuthResult.Error -> {
                        _state.value = LoginState(error = result.message)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onLoading() {
        _state.value = LoginState(isLoading = true)
    }
}