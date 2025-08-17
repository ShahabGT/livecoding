package ir.shahabazimi.livecoding.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val error: String? = null
)